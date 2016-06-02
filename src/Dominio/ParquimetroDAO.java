package Dominio;

import java.util.List;


public interface ParquimetroDAO {
    public List<Parquimetro> buscarTodos() throws ParquimetroDAOException;
    public Parquimetro buscarId(int id) throws ParquimetroDAOException;
    public Parquimetro buscarEndereco(String addres) throws ParquimetroDAOException;
    public void inserir(Parquimetro p) throws ParquimetroDAOException;
    public void remover() throws ParquimetroDAOException;
    
    
}
