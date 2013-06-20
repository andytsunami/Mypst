package br.com.mypst.teste;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.mypst.infra.DataUtil;

public class TestaDataUtil {
	
	public static void main(String[] args) {
		
		String dataEntrada = "2012-02-22T20:23:13";
		
		DataUtil dataUtil = new DataUtil();
		
		System.out.println(dataUtil.getCalendar(dataEntrada));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		Date date = new Date();
		Calendar calendar = dataUtil.getCalendar(dataEntrada);
		
		date = calendar.getTime();
		
		System.out.println(sdf.format(date));
		
		
		
		
		
	}

}
