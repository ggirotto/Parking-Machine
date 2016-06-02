package Persistencia;

import Dominio.Parquimetro;
import java.util.List;


public interface ParquimetroDAO {
    public List buscarTodos();
    public Parquimetro buscarId();
    public Parquimetro buscarEndereco();
    public void inserir(Parquimetro p);
    public void remover();
    
    
}
