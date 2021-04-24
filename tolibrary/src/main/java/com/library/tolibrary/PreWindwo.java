package com.library.tolibrary;

import java.time.LocalTime;
import org.jsoup.nodes.Document;

public class PreWindwo implements Runnable {
	private String r_url;
	private long startTime;
	String llString = "该座位已经被抢掉了,请换个座位";

	public void run() {
		int nums = 10;
		try {
			Document result = TeseMain.toFunction(this.r_url, this.startTime);
			if (result != null) {

				String re_msg = TeseMain.getJson(result);
				System.out.println(Thread.currentThread().getName() + "第一次结果：" + re_msg);
				while (isEnd(re_msg, nums)) {
					result = TeseMain.toFunction(this.r_url, this.startTime);
					re_msg = TeseMain.getJson(result);
					System.out.println(Thread.currentThread().getName() + "第" + (10 - nums + 1) + "结果：" + re_msg);
					nums--;
				}
				System.out.println("结束时间" + LocalTime.now());
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}

	public boolean isEnd(String re_msg, int nums) {
		return (!"预定座位成功".equals(re_msg) && nums > 0 && !"该座位已经被抢掉了,请换个座位".equals(re_msg));
	}

	public String getR_url() {
		return this.r_url;
	}

	public void setR_url(String r_url) {
		this.r_url = r_url;
	}

	public long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}