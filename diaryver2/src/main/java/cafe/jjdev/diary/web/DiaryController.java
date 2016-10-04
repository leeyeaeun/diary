package cafe.jjdev.diary.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.diary.service.DiaryService;
import cafe.jjdev.diary.service.Schedule;

@Controller
public class DiaryController {
	private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);
	@Autowired
	DiaryService diaryservice;
	
	@RequestMapping(value="/diary/ScheduleAdd",method =RequestMethod.POST)
	public String addSchedule(Schedule schedule){
		logger.info("param {}",schedule);//repeat값 on/ null
		diaryservice.addSchedule(schedule);
		return "redirect:/diary/scheduleList?scheduleDday="+schedule.getSrcScheduleDate();//원본날짜 
	}
	
	
	//오늘 날짜 구하기    * {} : 배열요청. 
	@RequestMapping(value={"/","/diary/","/diary/index"})
	public String diaryIndex(Model model,
							@RequestParam(value="ddayYear", defaultValue="0") int ddayYear,
							@RequestParam(value="ddayMonth", defaultValue="0") int ddayMonth,
							@RequestParam(value="ddayOption", defaultValue="default") String ddayOption){
		Map<String,Object> map = diaryservice.getOneDayList(ddayYear,ddayMonth,ddayOption);
		model.addAttribute("oneDayList",map.get("oneDayList"));
		model.addAttribute("ddayYear",map.get("ddayYear"));
		model.addAttribute("ddayMonth",map.get("ddayMonth"));
		return "/diary/index";
	}

	@RequestMapping(value="/diary/scheduleList")
	public String scheduleList(Model model , @RequestParam(value="scheduleDday")String scheduleDday){
		logger.info("PARAM DDAY :{}",scheduleDday);
		model.addAttribute("scheduleDday",scheduleDday);
		//DiaryService scheduleList 호출 ->모델 
		model.addAttribute("scheduleList", diaryservice.getScheduleListByDate(scheduleDday));
		return "/diary/scheduleList";
	}
}
