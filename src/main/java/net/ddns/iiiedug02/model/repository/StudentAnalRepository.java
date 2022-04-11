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
	      value = "select * ,100*countgender/Sum(countgender) over (partition by cid) as ratio from\r\n"
	      		+ "(select c.cid ,md.gender, count(md.gender) as countgender\r\n"
	      		+ "from　classmanagement c \r\n"
	      		+ "inner join member_details md \r\n"
	      		+ "on c.uid = md.uid \r\n"
	      		+ "group by c.cid, md.gender)as a where cid = ?1",
	      nativeQuery = true)
  public List<Map<String, Integer>> getgenderbyID(String cid);

  @Query(
	      value = "select *, floor(100*agecount/Sum(agecount) over (partition by cid)) AS ratio from\r\n"
	      		+ "(select * ,count(age) as agecount from (select c.cid, floor(DATEDIFF(DY,md.birthday,GETDATE())/365.25) as age  \r\n"
	      		+ "from classmanagement c join member_details md on c.uid = md.uid) as a group by cid, age) as a where cid= ?1",
	      nativeQuery = true)
  public List<Map<String, Integer>> getAgePercentbyID(String cid);
  
  @Query(
	      value = "SELECT *, 100*jobcount/Sum(jobcount) over (partition by cid) AS ratio\r\n"
	      		+ "FROM (select c.cid , md.job, count(md.job) as jobcount\r\n"
	      		+ "from classmanagement c \r\n"
	      		+ "inner join member_details md \r\n"
	      		+ "on c.uid = md.uid \r\n"
	      		+ "group by c.cid, md.job) as a where cid = ?1",
	      nativeQuery = true)
  public List<Map<String, Integer>> getJobPercentbyID(String cid);
  
  @Query(
	      value = "select date, sum(totalprice) as money from\r\n"
	      		+ "(select *, price*count(cid) as totalprice \r\n"
	      		+ "FROM(select convert(nvarchar(7),order_date,120) as date, c.cid , price from classmanagement c join class a on c.cid=a.cid)\r\n"
	      		+ "as a group by date, price, cid)as a group by date",
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

}
