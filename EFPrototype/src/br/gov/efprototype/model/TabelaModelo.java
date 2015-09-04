package br.gov.efprototype.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;
  
public class TabelaModelo extends AbstractTableModel {  
    private ArrayList linhas;  
    private String[] colunas = null;  
  
    // Métodos Geters  
    public String[] getColunas() {  
        return colunas;  
    }  
  
    public List getLinhas() {  
        return linhas;  
    }  
  
    // Métodos Seters  
    public void setColunas(String[] strings) {  
        colunas = strings;  
    }  
  
    public void setLinhas(ArrayList list) {  
        linhas = list;  
    }  
  
    // Construtores  
    public TabelaModelo() {  
  
    }  
  
    public TabelaModelo(ArrayList dados, String[] colunas) {  
        setLinhas(dados);  
        setColunas(colunas);  
    }  
  
    /** 
     * Retorna o numero de colunas no modelo 
     *  
     * @see javax.swing.table.TableModel#getColumnCount() 
     */  
    public int getColumnCount() {  
        return getColunas().length;  
    }  
  
    /** 
     * Retorna o numero de linhas existentes no modelo 
     *  
     * @see javax.swing.table.TableModel#getRowCount() 
     */  
    public int getRowCount() {  
        return getLinhas().size();  
    }  
  
    /** 
     * Obtem o valor na linha e coluna 
     *  
     * @see javax.swing.table.TableModel#getValueAt(int, int) 
     */  
    public Object getValueAt(int rowIndex, int columnIndex) {  
        // Obtem a linha, que é uma String []  
        String[] linha = (String[]) getLinhas().get(rowIndex);  
        // Retorna o objeto que esta na coluna  
        return linha[columnIndex];  
    }  
  
   
  
    public void setValueAt(Object value, int row, int col) {  
        // Obtem a linha, que é uma String []  
        String[] linha = (String[]) getLinhas().get(row);  
        // Altera o conteudo no indice da coluna passado  
        linha[col] = (String) value;  
        // dispara o evento de celula alterada  
        fireTableCellUpdated(row, col);  
    }  
  
    public void addRow(String[] dadosLinha) {  
        getLinhas().add(dadosLinha);  
        // Informa a jtable de que houve linhas incluidas no modelo  
        // COmo adicionamos no final, pegamos o tamanho total do modelo  
        // menos 1 para obter a linha incluida.  
        int linha = getLinhas().size() - 1;  
        fireTableRowsInserted(linha, linha);  
        return;  
    }  
  
    public void removeRow(int row) {  
        getLinhas().remove(0);  
        // informa a jtable que houve dados deletados passando a  
        // linha reovida  
        fireTableRowsDeleted(row, row);  
    }  
  
    /** 
     * Remove a linha pelo valor da coluna informada 
     *  
     * @param val 
     * @param col 
     * @return 
     */  
    public boolean removeRow(String val, int col) {  
        // obtem o iterator  
        Iterator i = getLinhas().iterator();  
        int linha = 0;  
        // Faz um looping em cima das linhas  
        while (i.hasNext()) {  
            // Obtem as colunas da linha atual  
            String[] linhaCorrente = (String[]) i.next();  
            linha++;  
            // compara o conteudo String da linha atual na coluna desejada  
            // com o valor informado  
            if (linhaCorrente[col].equals(val)) {  
                getLinhas().remove(linha);  
                // informa a jtable que houve dados deletados passando a  
                // linha removida  
                fireTableRowsDeleted(linha, linha);  
                return true;  
            }  
        }  
        // Nao encontrou nada  
        return false;  
    }  
  
}  	