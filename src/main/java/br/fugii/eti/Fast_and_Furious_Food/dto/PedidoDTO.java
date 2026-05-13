package br.fugii.eti.Fast_and_Furious_Food.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;


public class PedidoDTO {
    
@Valid
    @NotEmpty
    private List<ItemPedidoDTO> itens;
    
    public List<ItemPedidoDTO> getItens(){
        return itens;
        
    }
    
    public void setItens(List<ItemPedidoDTO> itens){
        this.itens = itens;
    }
}    
    

