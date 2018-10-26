package br.senap.sp.cfp127.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
	
	public static String converterParaPortugues(Date dt) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(dt);
	}
	
	public static String converterParaPortugues(Date dt, String formato) {
		SimpleDateFormat df = new SimpleDateFormat(formato);
		return df.format(dt);
	}
	
	public static int calcularIdade(Date dtIni, Date dtFim) {
		if(dtIni == null || dtFim == null) {
			return 0;
		}
		long tempo = (dtIni.getTime() - dtFim.getTime())/1000/60/60/24/30/12;
		return Math.toIntExact(tempo);
	}
	
	public static String converterParaAccess(String data) {
		SimpleDateFormat stringParaDate = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateParaString = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000000");
		Date dt = null;
		
		try {
			dt = stringParaDate.parse(data);
			data = dateParaString.format(dt);
		} catch (ParseException e) {
			
		}
		
		return data;
	}
	
	public static String converterString(String data) {
		SimpleDateFormat stringParaDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.000000");
		String dt = null;
		
		try {
			dt = stringParaDate.format(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dt;
	}
}
