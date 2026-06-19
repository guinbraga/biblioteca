package src;

import java.util.ArrayList;
import java.util.List;

public class Controle {

  public boolean emprestar(String aluno, int[] prazos, int num)
  {
	  boolean retorno=true;
	  /*Aqui vocÊ deve instaciar um objeto aluno*/
	  
	  //Verifica se o aluno existe
	  if (!a.verficaAluno())
	  {
		  System.out.println("Aluno Inexistente");
		  retorno = false;
       }
	  
	  //Verifica se o aluno possui algum Debito
	  if (!a.verificaDebito())
	  {
		  System.out.println("Aluno em Debito");
		  retorno = false;
       }
	  
	  //Caso o aluno não tenha débitos e exista
	  if(retorno)
	  {   
		  //Cria um conjunto de livros
		  List<Livro> livros = new ArrayList<Livro>();  
	     
		  /*Para cada livro verifica  se é exemplar da biblioteca 
                   e só deixará emprestar os livros que não são */
                  for(int i=0; i< num;i++)
		   {   Livro l = new Livro(codigos[i]);
		     //caso o livro não seja exemplar da biblioteca permite emprestar  
		     if (!l.verificaLivro())
			   livros.add(l); 
		   }
			
		   /*Chama o método delegado do aluno de emprestar cliente, passando o conjunto de livros como parametro caso tenha pelo menos um livro a ser emprestado*/
		   if (livros.size() > 0 )
		   {   
		     retorno = a.emprestar(livros);
		     return retorno;
		   }
		   else
		   return false;
		  
	  }
	  else
		  return retorno;
  }
	
}
