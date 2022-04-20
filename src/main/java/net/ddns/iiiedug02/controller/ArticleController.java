package net.ddns.iiiedug02.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.ddns.iiiedug02.helpers.ArticleHelper;
import net.ddns.iiiedug02.model.bean.ArticleBean;
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.TagBean;
import net.ddns.iiiedug02.model.service.ArticalService;
import net.ddns.iiiedug02.model.service.MemberService;
import net.ddns.iiiedug02.model.service.TagService;

@Controller
@RequestMapping(path = "/articles")
public class ArticleController {
    @Autowired
    private ArticalService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ArticleHelper articleHelper;

    // 含有 admin or teacher 角色的人才能新增/編輯/刪除文章
    private String ADMIN = "admin";
    private String TEACHER = "teacher";

    @GetMapping("")
    public String dispatch(HttpServletRequest request, Principal principal, Model model,
            String article, String category) {
        if (article != null)
            return getArticleByUUID(article, model);
        else if (category != null)
            return getArticlesByTagCategory(model, category);

        // 取得所有文章
        List<ArticleBean> articles = articleService.findAll();

        // 取得所有標籤
        List<TagBean> tags = tagService.findAll();

        // 將所有文章與標籤放入 model，jsp 可以取出來
        model.addAttribute("articles", articles);
        model.addAttribute("tags", tags);

        // 放入文章縮圖與內縮寫內容
        model.addAttribute("thumbnails", articleHelper.getThumbnails(articles));
        model.addAttribute("abbreviations", articleHelper.getAbbreviations(articles));

        return "article/articles";
    }

    // 進入 "發佈文章" 頁面
    // Principal principal: 如果使用者已經登入，這個物件才會存在，否則會是 null
    @GetMapping("/create")
    public String create(HttpServletRequest request, Principal principal, Model model) {
        // 判斷使用者是否有管理員角色，才可以進入發表文章頁面，否則直接返回文章列表頁面
        if (articleHelper.hasRole(principal, ADMIN) || articleHelper.hasRole(principal, TEACHER)) {
            // 從資料庫撈出所有的標籤
            List<TagBean> tags = tagService.findAll();

            // model 物件能夠將資料傳入到 jsp 裡面
            // 放入 model，之後 jsp 可以取得 tags 物件
            model.addAttribute("tags", tags);

            return "article/createArticle";
        }

        // 如果使用者沒有登入或是使用者沒有管理員角色，則返回文章列表頁面
        return "article/articles";
    }

    // 發佈文章(POST 請求)
    @PostMapping("/create")
    public String createArticle(HttpServletRequest request, Principal principal)
            throws ParseException {
        ArticleBean article = null;

        // 先判斷是否擁有管理者角色，如果沒有管理者角色則直接返回文章列表頁面
        if (!(articleHelper.hasRole(principal, ADMIN) || articleHelper.hasRole(principal, TEACHER)))
            // redirect 是重定向的意思，作用為讓瀏覽器再發一次請求到指定的頁面(在這裡是 articles)
            return "redirect:/articles";

        // 透過 username 去資料庫查出 member 物件，因為 principal 物件只能取得使用者名稱(在這裡是 nilm)
        Member member = memberService.findByUsername(articleHelper.getUsername(principal));

        if (member != null) {
            article = new ArticleBean();
            article.setUuid(articleHelper.generateUUID());

            // request.getParameter 取出前端表單(form)傳過來的資料
            // 把文章標題(title)與文章內容(content)取出來
            article.setTitle(request.getParameter("title"));
            article.setContent(request.getParameter("content"));

            article.setPageViews(0);
            article.setMember(member);
            article.setPublishTime(articleHelper.getDate());

            // 取得文章所選取的標籤(例如: 理想生活、學設計)
            // 這裡使用 request.getParameterValues 是因為含有多個標籤。
            String[] strings = request.getParameterValues("tags");

            // 因為是多對多關係，所以要把文章所選取的標籤新增到中介表(article_tag表)
            article.getTags().addAll(tagService.findByIdIn(articleHelper.getTagsId(strings)));

            // insert() 才是真的把資料寫入資料庫
            articleService.insert(article);

            // 新增文章之後，返回文章列表頁面
            return "redirect:/articles?create=success";
        }

        return "redirect:/articles";
    }

    // 透過文章的 UUID 來取得單篇文章內容，並且會跳轉到新的頁面瀏覽文章
    public String getArticleByUUID(String uuid, Model model) {
        // 透過 articleService.findByUuid 來取得單篇文章
        List<ArticleBean> articles = articleService.findByUuid(uuid);

        // 防止 BUG 出現(如:查出文章不只一篇或是查不到文章)，則直接返回文章列表頁面
        if (articles.size() != 1)
            return "article/articles";

        ArticleBean article = articles.get(0);

        // 將瀏覽次數取出來之後做 +1 的動作
        int views = article.getPageViews() + 1;

        // 把更新過後的瀏覽次數寫回資料庫
        articleService.updatePageViewsByUUID(article.getUuid(), views);
        model.addAttribute("article", articles.get(0));

        return "article/retrieveArticle";
    }

    // 根據文章標籤(category)來找出所有相關的文章
    public String getArticlesByTagCategory(Model model, String category) {
        List<TagBean> allTags = tagService.findAll();
        List<TagBean> tags = tagService.findByCategory(category);

        if (tags.size() == 1) {
            TagBean tag = tags.get(0);
            List<ArticleBean> articles = new ArrayList<>(tag.getArticles());

            model.addAttribute("tags", allTags);
            model.addAttribute("tag", tag);
            model.addAttribute("thumbnails", articleHelper.getThumbnails(articles));
            model.addAttribute("abbreviations", articleHelper.getAbbreviations(articles));
            model.addAttribute("articles", tag.getArticles());
        }

        return "article/articles";
    }

    // 進入編輯文章頁面，用的跟發佈文章是同一個頁面
    @GetMapping("/update/{uuid}")
    public String update(Principal principal, Model model, @PathVariable String uuid) {
        if (!(articleHelper.hasRole(principal, ADMIN) || articleHelper.hasRole(principal, TEACHER)))
            return "article/articles";

        List<ArticleBean> articles = articleService.findByUuid(uuid);

        if (articles.size() != 1)
            return "article/articles";

        ArticleBean article = articles.get(0);
        List<TagBean> tags = tagService.findAll();

        // 得到標籤 PK 的字串(e.g. "[1, 2]")
        String ids = article.getTags().stream().map(tag -> tag.getId()).collect(Collectors.toList())
                .toString();

        model.addAttribute("article", article);
        model.addAttribute("tags", tags);
        model.addAttribute("ids", ids);

        return "article/createArticle";
    }

    @PostMapping("/update/{uuid}")
    public String updateArticle(HttpServletRequest request, Principal principal,
            @PathVariable String uuid) {

        if (!(articleHelper.hasRole(principal, ADMIN) || articleHelper.hasRole(principal, TEACHER)))
            return "article/articles";

        List<ArticleBean> articles = articleService.findByUuid(uuid);

        if (articles.size() != 1)
            return "article/articles";

        Member member = memberService.findByUsername(articleHelper.getUsername(principal));

        if (member != null) {
            ArticleBean article = articles.get(0);
            article.setTitle(request.getParameter("title"));
            article.setContent(request.getParameter("content"));
            article.setMember(member);

            String[] strings = request.getParameterValues("tags");

            // 更新多對多表的方式(透過 lambda 表達式來完成較簡潔的語句)
            Set<TagBean> tags = tagService.findByIdIn(articleHelper.getTagsId(strings)).stream()
                    .collect(Collectors.toSet());

            // 更新文章的標籤
            article.setTags(tags);
            articleService.update(article);
        }

        // 更新文章之後，返回文章列表頁面
        return "redirect:/articles?update=success";
    }

    // 刪除文章
    @PostMapping("/delete")
    @ResponseBody
    public String deleteArticleByUUID(@RequestBody Map<String, String> params, Principal principal)
            throws JsonProcessingException {

        // 405 表示前端發過來的請求有問題
        String badRequest = "{\"response\":\"405\"}";

        // 200 請求表示成功
        String httpOk = "{\"response\":\"200\"}";

        // 判斷是不是ROLE_Admin發過來的請求
        if (!(articleHelper.hasRole(principal, ADMIN) || articleHelper.hasRole(principal, TEACHER)))
            return badRequest;

        String uuid = params.get("uuid");

        if (uuid == null)
            return badRequest;
        if (articleService.deleteByUuid(uuid) == 1)
            return httpOk;

        return badRequest;
    }
}
