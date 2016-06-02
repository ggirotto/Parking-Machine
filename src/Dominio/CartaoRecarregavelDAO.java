package Dominio;

import java.util.List;


public interface CartaoRecarregavelDAO {
    public List<CartaoRecarregavel> buscarTodos() throws CartaoRecarregavelDAOException;
    public CartaoRecarregavel buscarId(int id) throws CartaoRecarregavelDAOException;
    public int getSaldo(CartaoRecarregavel c) throws CartaoRecarregavelDAOException;
    public boolean exist(CartaoRecarregavel c) throws CartaoRecarregavelDAOException;
    public void inserir(CartaoRecarregavel c) throws CartaoRecarregavelDAOException;
    public void remove() throws CartaoRecarregavelDAOException;
    public boolean possuiSaldo(CartaoRecarregavel c, int valor) throws CartaoRecarregavelDAOException;
    
    
}
