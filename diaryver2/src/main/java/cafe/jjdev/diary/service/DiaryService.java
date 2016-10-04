package cafe.jjdev.diary.service;

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
	
	//스케쥴리스트
	public List<Schedule> getScheduleListByDate(String scheduleDate){
		//반복X
		List<Schedule> scheduleList = scheduleDao.selectScheduleListByDate(scheduleDate);
		//반복O
		scheduleList.addAll(scheduleDao.selectRepeatScheduleListByDate(scheduleDate.substring(5)));
		
		
		return scheduleList;
	}
	
	
	public int addSchedule(Schedule schedule){
		//repeat에 체크여부 ...
		if(schedule.getRepeat()==null){
			return scheduleDao.insertSchedule(schedule);
		}else{
			return scheduleDao.insertRepeatSchedule(schedule);
		}	
	}
	
	
	public Map<String , Object> getOneDayList(int ddayYear,int ddayMonth,String ddayOption){
		Map map = new HashMap<String , Object>();
		//dday : ?년 + ?월 + 1일
		Calendar dday= Calendar.getInstance();	//오늘날짜
		dday.set(Calendar.DATE,1);
		if(ddayOption.equals("prev")){
			dday.set(ddayYear, ddayMonth,1);
			dday.add(Calendar.MONTH, -1);//1월에서 -1하면 12월이 될수있도록 메서드를 사용
		}else if(ddayOption.equals("next")){
			dday.set(ddayYear, ddayMonth,1);
			dday.add(Calendar.MONTH, 1);//12월에서 +1하면 1월이 될수있도록 메서드를 사용
		}
		
		//1일의 요일 : 앞부분 공백구하기
		int firstWeek = dday.get(Calendar.DAY_OF_WEEK);
		List<OneDay> oneDayList = new ArrayList<OneDay>();
		//마지막날짜
		int endDay= dday.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//List size(=날짜가 들어갈 <td>의개수)
		int listSize = (firstWeek-1)+endDay;
		if(listSize%7!=0){
			listSize=listSize+(7-(listSize%7));
		}
		//이전달의 마지막날 
		Calendar preMonth = Calendar.getInstance();	
		preMonth.set(Calendar.MONTH, dday.MONTH-1);
		int preLastDay= preMonth.getActualMaximum(Calendar.DATE);
		int beginSpace= preLastDay -(firstWeek-2);
		int endSpace=1;
		for(int i =0; i<listSize;i++){
			OneDay oneDay;
			//앞의공백
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
				//특정 년도의 oneDay스케쥴 타이틀 리스트
				List<Schedule> scheduleList = scheduleDao.selectScheduleTitleListByDate(scheduleDate);
				//매년 oneDay스케쥴 타이틀 리스트
				List<Schedule> repeatScheduleList = scheduleDao.selectRepeatScheduleTitleListByDate(scheduleDate.substring(5));
				
				scheduleList.addAll(repeatScheduleList);
				//두개의 스케쥴을 합침 
				oneDay.setScheduleList(scheduleList);
				
				//oneDay와 diary테이블 ResultSet반복시키며 비교매핑
			//뒤의공백
			}else {
				oneDay = new OneDay();
				oneDay.setDay(endSpace);
			}
			oneDayList.add(oneDay);
		}
		map.put("oneDayList", oneDayList);
		map.put("ddayYear", dday.get(Calendar.YEAR));
		map.put("ddayMonth", dday.get(Calendar.MONTH));
		
		return map;		
	}
/*	//단위테스트시 메인을 만들어서 호출하면된다. 추후 안지워도 문제없음  
	public static void main(String[] args){
		new DiaryService().getOneDayList();
	}*/
}
