package net.ddns.iiiedug02.model.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.ddns.iiiedug02.model.bean.StudentAnalysis;

public interface StudentAnalRepository extends JpaRepository<StudentAnalysis, Integer> {

  @Override
  @Query(value = "select * from studentAnalysis", nativeQuery = true)
  public List<StudentAnalysis> findAll();
  
  @Query(
	      value = "SELECT *,\r\n"
	      		+ "	100 * countgender / Sum(countgender) OVER (PARTITION BY cid) AS ratio\r\n"
	      		+ "FROM (\r\n"
	      		+ "	SELECT c.cid,\r\n"
	      		+ "		md.gender,\r\n"
	      		+ "		count(md.gender) AS countgender\r\n"
	      		+ "	FROM classmanagement c\r\n"
	      		+ "	INNER JOIN member_details md ON c.uid = md.uid\r\n"
	      		+ "	WHERE md.gender IS NOT NULL\r\n"
	      		+ "	GROUP BY c.cid,\r\n"
	      		+ "		md.gender\r\n"
	      		+ "	) AS a\r\n"
	      		+ "WHERE cid = ?1",
	      nativeQuery = true)
  public List<Map<String, Integer>> getgenderbyID(String cid);

  @Query(
	      value = "SELECT *,\r\n"
	      		+ "	floor(100 * agecount / Sum(agecount) OVER (PARTITION BY cid)) AS ratio\r\n"
	      		+ "FROM (\r\n"
	      		+ "	SELECT *,\r\n"
	      		+ "		count(age) AS agecount\r\n"
	      		+ "	FROM (\r\n"
	      		+ "		SELECT c.cid,\r\n"
	      		+ "			floor(DATEDIFF(DY, md.birthday, GETDATE()) / 365.25) AS age\r\n"
	      		+ "		FROM classmanagement c\r\n"
	      		+ "		JOIN member_details md ON c.uid = md.uid\r\n"
	      		+ "		WHERE md.birthday IS NOT NULL\r\n"
	      		+ "		) AS a\r\n"
	      		+ "	GROUP BY cid,\r\n"
	      		+ "		age\r\n"
	      		+ "	) AS a\r\n"
	      		+ "WHERE cid = ?1",
	      nativeQuery = true)
  public List<Map<String, Integer>> getAgePercentbyID(String cid);
  
  @Query(
	      value = "SELECT *,\r\n"
	      		+ "	100 * jobcount / Sum(jobcount) OVER (PARTITION BY cid) AS ratio\r\n"
	      		+ "FROM (\r\n"
	      		+ "	SELECT c.cid,\r\n"
	      		+ "		md.job,\r\n"
	      		+ "		count(md.job) AS jobcount\r\n"
	      		+ "	FROM classmanagement c\r\n"
	      		+ "	INNER JOIN member_details md ON c.uid = md.uid\r\n"
	      		+ "	WHERE md.job IS NOT NULL\r\n"
	      		+ "	GROUP BY c.cid,\r\n"
	      		+ "		md.job\r\n"
	      		+ "	) AS a\r\n"
	      		+ "WHERE cid = ?1",
	      nativeQuery = true)
  public List<Map<String, Integer>> getJobPercentbyID(String cid);
  
  @Query(
	      value = "SELECT DATE,\r\n"
	      		+ "	sum(totalprice) AS MONEY\r\n"
	      		+ "FROM (\r\n"
	      		+ "	SELECT *,\r\n"
	      		+ "		price * count(cid) AS totalprice\r\n"
	      		+ "	FROM (\r\n"
	      		+ "		SELECT convert(NVARCHAR(7), order_date, 120) AS DATE,\r\n"
	      		+ "			c.cid,\r\n"
	      		+ "			price\r\n"
	      		+ "		FROM classmanagement c\r\n"
	      		+ "		JOIN class a ON c.cid = a.cid\r\n"
	      		+ "		WHERE year(c.order_date) = 2022\r\n"
	      		+ "		) AS a\r\n"
	      		+ "	GROUP BY DATE,\r\n"
	      		+ "		price,\r\n"
	      		+ "		cid\r\n"
	      		+ "	) AS a\r\n"
	      		+ "GROUP BY DATE",
	      nativeQuery = true)
  public List<Map<String, Integer>> getMoney();
  
  @Query(
	      value = "select cid,job,jobcount from (select cid,job,jobcount, rank() over(partition by cid order by jobcount DESC) as rank from \r\n"
	      		+ "(select c.cid , md.job, count(md.job) as jobcount\r\n"
	      		+ "from　classmanagement c \r\n"
	      		+ "inner join member_details md \r\n"
	      		+ "on c.uid = md.uid \r\n"
	      		+ "group by c.cid, md.job) as b) as b where rank = 1",
	      nativeQuery = true)
  public List<Map<String, Integer>> mostjob();
  
  @Query(
	      value = "select c.cid, a.title, a.class_type, count(c.cid) as count from classmanagement c join class a on c.cid=a.cid \r\n"
	      		+ "group by c.cid, a.title, a.class_type ",
	      nativeQuery = true)
  public List<Map<String, Integer>> getClassList();

}
