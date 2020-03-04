package br.sc.senac.model.executavel.exercicio1;

import br.sc.senac.model.dao.exercicio1.EnderecoDAO;
import br.sc.senac.model.vo.exercicio1.Endereco;

public class Executavel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EnderecoDAO endDAO = new EnderecoDAO();
		Endereco enderecoConsultado = endDAO.consultarPorId(3);
		System.out.println("Endereço 3: "+enderecoConsultado.toString());
		
		

	}

}
