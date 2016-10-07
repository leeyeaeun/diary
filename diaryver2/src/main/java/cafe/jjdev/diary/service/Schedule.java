package cafe.jjdev.diary.service;

public class Schedule {
	private int scheduleNo;
	private String scheduleDate; 
	private String scheduleTitle;
	private String scheduleMemo;
	private String schedulePlace;
	private String scheduleFontColor;
	private String repeat;
	private String srcScheduleDate;
	private String scheduleTime;
	
	public int getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getScheduleTitle() {
		return scheduleTitle;
	}
	public void setScheduleTitle(String scheduleTitle) {
		this.scheduleTitle = scheduleTitle;
	}
	public String getScheduleMemo() {
		return scheduleMemo;
	}
	public void setScheduleMemo(String scheduleMemo) {
		this.scheduleMemo = scheduleMemo;
	}
	public String getSchedulePlace() {
		return schedulePlace;
	}
	public void setSchedulePlace(String schedulePlace) {
		this.schedulePlace = schedulePlace;
	}
	public String getScheduleFontColor() {
		return scheduleFontColor;
	}
	public void setScheduleFontColor(String scheduleFontColor) {
		this.scheduleFontColor = scheduleFontColor;
	}
	public String getRepeat() {
		return repeat;
	}
	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
	public String getSrcScheduleDate() {
		return srcScheduleDate;
	}
	public void setSrcScheduleDate(String srcScheduleDate) {
		this.srcScheduleDate = srcScheduleDate;
	}
	public String getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	@Override
	public String toString() {
		return "Schedule [scheduleNo=" + scheduleNo + ", scheduleDate=" + scheduleDate + ", scheduleTitle="
				+ scheduleTitle + ", scheduleMemo=" + scheduleMemo + ", schedulePlace=" + schedulePlace
				+ ", scheduleFontColor=" + scheduleFontColor + ", repeat=" + repeat + ", srcScheduleDate="
				+ srcScheduleDate + ", scheduleTime=" + scheduleTime + "]";
	}

	
}
