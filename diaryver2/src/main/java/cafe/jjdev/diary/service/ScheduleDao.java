package cafe.jjdev.diary.service;

import java.util.List;
import java.util.Map;

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
	
	//����ȭ�� 
	public Schedule selectForRepeatScheduleUpdate (int scheduleNo){
		return sqlSession.selectOne(NS+".selectForRepeatScheduleUpdate", scheduleNo);
	}
	
	public Schedule selectForScheduleUpdate (int scheduleNo){
		return sqlSession.selectOne(NS+".selectForScheduleUpdate", scheduleNo);
	}
	//����
	public int deleteSchedule (int scheduleNo){
		return sqlSession.delete(NS+".deleteSchedule",scheduleNo);
	}
	
	public int deleteRepeatSchedule (int scheduleNo){
		return sqlSession.delete(NS+".deleteRepeatSchedule",scheduleNo);
	}
	
	//�ų� �ݺ� ������title�� select
	public List<Schedule> selectRepeatScheduleTitleListByDate(String scheduleDate){
		return sqlSession.selectList(NS+".selectRepeatScheduleTitleListByDate",scheduleDate);
	}
	//Ư����¥ ������title�� select
	public List<Schedule> selectScheduleTitleListByDate(String scheduleDate){
		return sqlSession.selectList(NS+".selectScheduleTitleListByDate",scheduleDate);
	}
	
	
	// �ݺ� list select
	public List<Schedule> selectRepeatScheduleListByDate(String scheduleDate){
		return sqlSession.selectList(NS+".selectRepeatscheduleListByDate",scheduleDate);
	}
	
	//�ݺ����� list select
	public List<Schedule> selectScheduleListByDate(String scheduleDate){
		return sqlSession.selectList(NS+".selectscheduleListByDate",scheduleDate);
	}
	
	
	
	/* repeat : "repeat" == �ݺ� ������ �Է�*/
	public int insertRepeatSchedule(Schedule schedule){
		return sqlSession.insert(NS+".insertRepeatSchedule",schedule);
	}
	
	/* repeat : null == ������ �Է�*/
	public int insertSchedule(Schedule schedule){
		return sqlSession.insert(NS+".insertSchedule",schedule);
	}
}
