package com.uel.biblioteca.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Titulo {
	
	int prazo;

public Titulo(int codigo)
{
	this.prazo = codigo+1;
}


}