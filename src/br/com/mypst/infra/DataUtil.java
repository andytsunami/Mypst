package br.com.mypst.infra;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtil {
	

	
	public  Calendar getCalendar(String data){
		
		SimpleDateFormat padraoEntrada = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		
		try {
			date = padraoEntrada.parse(data.replaceAll("T", " "));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		calendar.setTime(date);
		
		return calendar;
		
	}

}
