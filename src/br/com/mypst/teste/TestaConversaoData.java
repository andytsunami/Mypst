package br.com.mypst.teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class TestaConversaoData {

	public static void main(String[] args) {

		String dataEntrada = "2012-02-22T20:23:12";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date data = new Date();
		try {
			data = sdf.parse(dataEntrada.replaceAll("T", " "));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		System.out.println(sdf2.format(data));

		System.out.println(data.toString());
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(data);
		
		
		

	}

}
