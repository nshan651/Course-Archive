/* Question 1 
select co.songcode,s.songTitle, tracknumber from composedof co ,song s where s.songcode = co.songcode and cdcode = (
select cdcode from topcds where year = 2003 and rating = 1)
order by tracknumber
*/

/* Question 2
select mg.groupcode,groupname,count(*) as numberOfTop10CDs
from musicalgroup mg, cd c, topcds tc
where mg.groupcode = c.groupcode and c.cdcode = tc.cdcode and tc.rating <= 10
group by mg.groupcode, mg.groupname
order by numberOfTop10CDs desc
*/

/* Question 3
select c.year, max(subquery.numberoftracks) as maxNumber,min(subquery.numberoftracks) as minNumber,avg(subquery.numberoftracks) as avgNumber
from cd c,
(select cdcode, count(*) as numberoftracks
from composedof
group by cdcode) subquery
where year >= 2000 and c.cdcode = subquery.cdcode
group by c.year
order by c.year
*/

/* Question 4
select mg.groupcode,mg.groupname, cd.labelid,rl.labelname,sum(cd.numbersold) as totalNumberSold from musicalgroup mg, cd, recordinglabel rl
where mg.groupcode = cd.groupcode and cd.labelid = rl.labelid
group by mg.groupcode,mg.groupname, cd.labelid,rl.labelname
order by totalNumberSold DESC
*/

/* Question 5
select distinct wr.artistid, art.firstname,art.lastname,art.yearborn from artist art,writtenby wr, member mb, topsongs ts where art.artistid = wr.artistid
and ts.songcode = wr.songcode
and ts.rating <=5 and not exists (select * from member where wr.artistid = member.artistid and member.todate = 0)
*/

/* Question 6
select m.groupCode, m.groupName
from musicalgroup m, cd c, recordinglabel r
where m.groupcode = c.groupcode and c.labelid = r.labelid and r.labelName = 'Country Club'group by m.groupcode, m.groupName
intersect
select m.groupCode, m.groupName
from musicalgroup m, cd c, recordinglabel r
where m.groupcode = c.groupcode and c.labelid = r.labelid and r.labelName = 'Gray Dot Records'group by m.groupcode, m.groupName
intersect
select m.groupCode, m.groupName
from musicalgroup m, cd c, recordinglabel r
where m.groupcode = c.groupcode and c.labelid = r.labelid and r.labelName = 'Disney Records'group by m.groupcode, m.groupName
*/
