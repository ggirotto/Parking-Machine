package Persistencia;

import Dominio.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;


public class ParquimetroDAOBinSerial implements ParquimetroDAO {
    private File arquivo;
    
    public ParquimetroDAOBinSerial(File arq){
        arquivo = arq;
    }
    
    @Override
    public List<Parquimetro> buscarTodos() throws ParquimetroDAOException{
        List<Parquimetro> resultado = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))){
            resultado = (ArrayList<Parquimetro>)ois.readObject();
            return resultado;
        } catch(Exception e) {
            throw new ParquimetroDAOException("Falha na consulta de parquimetros", e);
        }
    }

    @Override
    public Parquimetro buscarId(int id) throws ParquimetroDAOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Parquimetro buscarEndereco(String addres) throws ParquimetroDAOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inserir(Parquimetro p) throws ParquimetroDAOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover() throws ParquimetroDAOException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
