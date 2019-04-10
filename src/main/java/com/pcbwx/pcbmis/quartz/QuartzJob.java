package com.pcbwx.pcbmis.quartz;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.pcbwx.pcbmis.common.ConfigProperties;
import com.pcbwx.pcbmis.enums.ConfigEnum;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.utils.DataUtil;


/**
 * QuartzJob Demo类
 * @author 王海龙
 * @version 1.1
 *
 */
@Configuration
@EnableScheduling
public class QuartzJob {

	private static Logger logger = Logger.getLogger(QuartzJob.class);

	@Autowired
	private SupportService supportService;

	@Autowired
	private ImportTask importTask;
	
	private static AtomicInteger reloadFlag = new AtomicInteger();	
	@Scheduled(cron = "${reload.timer.cron.expression}")
	public void reloadCache() {
		logger.info("reloadCache的任务调度！！！");
		if (reloadFlag.incrementAndGet() > 1) {
			reloadFlag.decrementAndGet();
			return;
		}
		
		try {
			supportService.doReloadCache();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString());
		}
		
		reloadFlag.decrementAndGet();		
		logger.info("reloadCache的任务调度结束！！！");
	}

	private static AtomicInteger dataSyncFlag = new AtomicInteger();	
	@Scheduled(cron = "${dataSync.timer.cron.expression}")
	public void dataSync() {
		if (dataSyncFlag.incrementAndGet() > 1) {
			dataSyncFlag.decrementAndGet();
			return;
		}
		logger.info("dataSync的任务调度！！！");

		try {
			importTask.importRecords();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		dataSyncFlag.decrementAndGet();		
		logger.info("dataSync的任务调度结束！！！");
	}
	
	private static AtomicInteger behaviourFlag = new AtomicInteger();
//	@Scheduled(cron = "${behaviourRecord.timer.cron.expression}")
	public void doBehaviourRecord(){
		if (behaviourFlag.incrementAndGet() > 1) {
			behaviourFlag.decrementAndGet();
			return;
		}
		logger.info("behaviourRecord的任务调度！！！");
		Integer debug = ConfigProperties.getPropertyInt(ConfigEnum.NOT_BEHAVIOUR_RECORD);
		if (!DataUtil.equals(debug, 1)) {
//			supportService.dobehaviour(cm);
		}
		behaviourFlag.decrementAndGet();		
		logger.info("behaviourRecord的任务调度结束！！！");
	}
}
