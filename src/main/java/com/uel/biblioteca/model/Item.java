package com.uel.biblioteca.model;

import java.util.Calendar;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    //Cada Item tem um livro associado
	Livro livro;
    Date dataDevolucao;
    
    //Quando um item � criado, um livro � associado a ele
	public Item(Livro livro) {
		super();
		this.livro = livro;
		
	}
	
	//Metodo para calcular a data de Devolucao de cada Item
	public Date calculaDataDevolucao(Date data)
	{  dataDevolucao=data;
	   Calendar calendar = Calendar.getInstance();
	   calendar.setTime(data);
	   calendar.add(Calendar.DATE, livro.verPrazo());
           dataDevolucao = calendar.getTime();
	   return dataDevolucao;
	}

}
