package Persistencia;

import Dominio.CartaoRecarregavel;
import java.util.List;


public interface CartaoRecarregavelDAO {
    public List buscarTodos();
    public CartaoRecarregavel buscarId();
    public int getSaldo(CartaoRecarregavel c);
    public boolean exist(CartaoRecarregavel c);
    public void inserir(CartaoRecarregavel c);
    public void remove();
    public boolean possuiSaldo(CartaoRecarregavel c, int valor);
    
    
}
