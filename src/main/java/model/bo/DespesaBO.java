package model.bo;

import java.util.ArrayList;


import model.dao.DespesaDAO;
import model.vo.DespesaVO;
import model.vo.UsuarioVO;

/**
 * Classe responsável pelas Regras de Negócio da despesa.
 * 
 * @author Adriano de Melo
 *
 */
public class DespesaBO {

	public void cadastrarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		despesaVO = despesaDAO.salvar(despesaVO);
		if (despesaVO.getId() > 0) {
			System.out.println("\nDespesa cadastrada com Sucesso.");
		} else {
			System.out.println("\nNão foi possível cadastrar a Despesa.");
		}
	}

	public void excluirDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		if (despesaDAO.existeRegistroPorIdDespesa(despesaVO.getId())) {
			if (despesaDAO.excluir(despesaVO.getId())) {
				System.out.println("\nDespesa excluída com Sucesso.");
			} else {
				System.out.println("\nNão foi possível excluir a Despesa.");
			}
		} else {
			System.out.println("\nDespesa não existe na base da dados.");
		}
	}

	public void atualizarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		if (despesaDAO.existeRegistroPorIdDespesa(despesaVO.getId())) {
			if (despesaDAO.alterar(despesaVO)) {
				System.out.println("\nDespesa atualizada com Sucesso.");
			} else {
				System.out.println("\nNão foi possível atualizar a Despesa.");
			}
		} else {
			System.out.println("\nDespesa ainda não foi cadastrada.");
		}
	}

	public ArrayList<DespesaVO> consultarTodasDespesasBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		ArrayList<DespesaVO> despesasVO = despesaDAO.consultarDespesasDoUsuario(despesaVO.getIdUsuario());
		if (despesasVO.isEmpty()) {
			System.out.println("\nLista de Despesas está vazia.");
		}
		return despesasVO;
	}

	public DespesaVO consultarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		DespesaVO despesa = despesaDAO.consultarPorId(despesaVO.getId());
		if (despesa == null) {
			System.out.println("\nDespesa não Localizada.");
		}
		return despesa;
	}

	public ArrayList<DespesaVO> consultarTodasCategorias() {
		DespesaDAO despesaDAO = new DespesaDAO();
		return despesaDAO.consultarTodasCategorias();
	}

	public ArrayList<DespesaVO> consultarDespesas(UsuarioVO nomeUsuario, String categoriaDespesa) {
		ArrayList<DespesaVO> despesas = new ArrayList();
		DespesaDAO despesaDAO = new DespesaDAO();
		
		if(nomeUsuario == null && categoriaDespesa.isEmpty()) {
			despesas = despesaDAO.consultarTodos();
		} else if(nomeUsuario != null && categoriaDespesa.isEmpty()) {
			despesas = despesaDAO.consultarTodosPorUsuario(nomeUsuario);
		} else if(nomeUsuario == null && !categoriaDespesa.isEmpty()) {
			despesas = despesaDAO.consultarPorCategoria(categoriaDespesa);
		} else if(nomeUsuario != null && !categoriaDespesa.isEmpty()) {
			despesas = despesaDAO.consultarDespesasDoUsuarioPorCategoria(nomeUsuario, categoriaDespesa);
		}
		
		return despesas;
	}

}
