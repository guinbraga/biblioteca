package com.uel.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Debito {
	
	private int codigoAluno;
	
	public boolean verificaDebito()
	{
	//codigo aleatorio para definir se o aluno tem d�bito
	//� necess�rio fazer a verifica��o de forma persistente
	if(this.codigoAluno == 4)
		 return false;
	 else
		return true;
	}

}