package cafe.jjdev.diary.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class ScheduleDao {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleDao.class);
	@Autowired
	private SqlSessionTemplate sqlSession;
	private final String NS ="cafe.jjdev.diary.service.ScheduleMapper";
	
	
	//매년 반복 스케쥴title의 select
	public List<Schedule> selectRepeatScheduleTitleListByDate(String scheduleDate){
		return sqlSession.selectList(NS+".selectRepeatScheduleTitleListByDate",scheduleDate);
	}
	//특정날짜 스케쥴title의 select
	public List<Schedule> selectScheduleTitleListByDate(String scheduleDate){
		return sqlSession.selectList(NS+".selectScheduleTitleListByDate",scheduleDate);
	}
	
	
	// 반복 list select
	public List<Schedule> selectRepeatScheduleListByDate(String scheduleDate){
		return sqlSession.selectList(NS+".selectRepeatscheduleListByDate",scheduleDate);
	}
	
	//반복없는 list select
	public List<Schedule> selectScheduleListByDate(String scheduleDate){
		return sqlSession.selectList(NS+".selectscheduleListByDate",scheduleDate);
	}
	
	
	
	/* repeat : "repeat" == 반복 스케쥴 입력*/
	public int insertRepeatSchedule(Schedule schedule){
		return sqlSession.insert(NS+".insertRepeatSchedule",schedule);
	}
	
	/* repeat : null == 스케쥴 입력*/
	public int insertSchedule(Schedule schedule){
		return sqlSession.insert(NS+".insertSchedule",schedule);
	}
}
