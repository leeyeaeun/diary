package cafe.jjdev.diary.service;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {	
	private static final Logger logger = LoggerFactory.getLogger(DiaryService.class);
	@Autowired
	private ScheduleDao scheduleDao;
	//����ó��
	public int scheduleModify(Schedule schedule){
		//�ݺ�X ->�ݺ� O�� ����� schedule���̺��� ������ ����� 
		if(schedule.getRepeat()==""||schedule.getRepeat()==null){//�ݺ�x
			return scheduleDao.insertSchedule(schedule);
		}else if(schedule.getRepeat().equals("repeat")){//�ݺ�O
			return scheduleDao.insertRepeatSchedule(schedule);
		}

		return -1;
	}

	//����ȭ��
	public Schedule scheduleModifyForm(Schedule schedule){
		logger.info("schedule {} ",schedule.toString());
		if(schedule.getRepeat()==""||schedule.getRepeat()==null){
			return scheduleDao.selectForScheduleUpdate(schedule.getScheduleNo());
		}else{
			return scheduleDao.selectForRepeatScheduleUpdate(schedule.getScheduleNo());
		}
	
	}
	//����
	public int scheduleRemove(Schedule schedule){
		logger.info("schedule.getRepeat() : {}",schedule.getRepeat());
		if(schedule.getRepeat()==""||schedule.getRepeat()==null){//
			return scheduleDao.deleteSchedule(schedule.getScheduleNo());
		}else if(schedule.getRepeat().equals("repeat")){
			return scheduleDao.deleteRepeatSchedule(schedule.getScheduleNo());
		}
			return -1;//�����߻��� -1����
	}
	
	//�����츮��Ʈ
	public List<Schedule> getScheduleListByDate(String scheduleDate){
		//�ݺ�X
		List<Schedule> scheduleList = scheduleDao.selectScheduleListByDate(scheduleDate);
		//�ݺ�O
		scheduleList.addAll(scheduleDao.selectRepeatScheduleListByDate(scheduleDate.substring(5)));
		
		
		return scheduleList;
	}
	
	
	public int addSchedule(Schedule schedule){
		//repeat�� üũ���� ...
		logger.info("schedule.getRepeat() !! : {}",schedule.getRepeat());
		if(schedule.getRepeat()==""||schedule.getRepeat()==null){
			return scheduleDao.insertSchedule(schedule);
		}else if(schedule.getRepeat().equals("repeat")){
			return scheduleDao.insertRepeatSchedule(schedule);
		}	
		return -1; //error
	}
	
	//index ������
	public Map<String , Object> getOneDayList(int ddayYear,int ddayMonth,String ddayOption){
		Map map = new HashMap<String , Object>();
		//dday : ?�� + ?�� + 1��
		Calendar dday= Calendar.getInstance();	//���ó�¥
		dday.set(Calendar.DATE,1);
		if(ddayOption.equals("prev")){
			dday.set(ddayYear, ddayMonth,1);
			dday.add(Calendar.MONTH, -1);//1������ -1�ϸ� 12���� �ɼ��ֵ��� �޼��带 ���
		}else if(ddayOption.equals("next")){
			dday.set(ddayYear, ddayMonth,1);
			dday.add(Calendar.MONTH, 1);//12������ +1�ϸ� 1���� �ɼ��ֵ��� �޼��带 ���
		}
		
		//1���� ���� : �պκ� ���鱸�ϱ�
		int firstWeek = dday.get(Calendar.DAY_OF_WEEK);
		List<OneDay> oneDayList = new ArrayList<OneDay>();
		//��������¥
		int endDay= dday.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//List size(=��¥�� �� <td>�ǰ���)
		int listSize = (firstWeek-1)+endDay;
		if(listSize%7!=0){
			listSize=listSize+(7-(listSize%7));
		}
		//�������� �������� 
		Calendar preMonth = Calendar.getInstance();	
		preMonth.set(Calendar.MONTH, dday.MONTH-1);
		int preLastDay= preMonth.getActualMaximum(Calendar.DATE);
		int beginSpace= preLastDay -(firstWeek-2);
		int endSpace=1;
		for(int i =0; i<listSize;i++){
			OneDay oneDay;
			//���ǰ���
			if(i<(firstWeek-1)){
				oneDay = new OneDay();
				oneDay.setDay(beginSpace);
				beginSpace++;
			}else if(i<endDay+(firstWeek-1)){
				oneDay = new OneDay();
				oneDay.setDay((i+1)-(firstWeek-1));
				oneDay.setYear(dday.get(Calendar.YEAR));
				oneDay.setMonth(dday.get(Calendar.MONTH)+1);
				String scheduleDate = oneDay.getYear()+"-"+oneDay.getMonth()+"-"+oneDay.getDay();
				//Ư�� �⵵�� oneDay������ Ÿ��Ʋ ����Ʈ
				List<Schedule> scheduleList = scheduleDao.selectScheduleTitleListByDate(scheduleDate);
				//�ų� oneDay������ Ÿ��Ʋ ����Ʈ
				List<Schedule> repeatScheduleList = scheduleDao.selectRepeatScheduleTitleListByDate(scheduleDate.substring(5));
				
				scheduleList.addAll(repeatScheduleList);
				//�ΰ��� �������� ��ħ 
				oneDay.setScheduleList(scheduleList);
				
				//oneDay�� diary���̺� ResultSet�ݺ���Ű�� �񱳸���
			//���ǰ���
			}else {
				oneDay = new OneDay();
				oneDay.setDay(endSpace);
				endSpace++;
			}
			oneDayList.add(oneDay);
		}
		OneDay today = new OneDay();
		Calendar getToDay= Calendar.getInstance();	
		today.setDay(getToDay.get(Calendar.DATE));
		today.setMonth(getToDay.get(Calendar.MONTH)+1);
		today.setYear(getToDay.get(Calendar.YEAR));
		map.put("oneDayList", oneDayList);
		map.put("ddayYear", dday.get(Calendar.YEAR));
		map.put("ddayMonth", dday.get(Calendar.MONTH));
		map.put("today", today);
		
		return map;		
	}
/*	//�����׽�Ʈ�� ������ ���� ȣ���ϸ�ȴ�. ���� �������� ��������  
	public static void main(String[] args){
		new DiaryService().getOneDayList();
	}*/
}
