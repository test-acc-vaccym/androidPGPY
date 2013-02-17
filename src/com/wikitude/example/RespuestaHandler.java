package com.wikitude.example;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

public class RespuestaHandler extends JsonHttpResponseHandler{
	
	private VisorInterface activity;
	
	public RespuestaHandler(VisorInterface activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}
	
	@Override
	 public void onSuccess(JSONObject jObject){ 
		
		try {
			
			String tipoRespuesta=jObject.get("codigo").toString();
			if(tipoRespuesta.equals("100")){
				Gson gson = new Gson();
				ControladorPDIs controlador = ControladorPDIs.getInstance();
				List<PuntoDeInteres> myTypes = null;
				myTypes = gson.fromJson(jObject.getString("mensaje"),
						new TypeToken<List<PuntoDeInteres>>() {
						}.getType());
				ArrayList<PuntoDeInteres> puntosDeInteres = (ArrayList<PuntoDeInteres>) myTypes;
				controlador.setPuntosDeInteres(puntosDeInteres);
				controlador.setPuntosDeInteresJArray(jObject.getString("mensaje"));
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		//
		activity.loadSampleWorld();
		
	
	     
	 }   
	 @Override
	 public void onFailure(Throwable arg0){
		 System.out.println("Se jodio el handler : "+arg0+" aqui termina el error");
	 }
}
