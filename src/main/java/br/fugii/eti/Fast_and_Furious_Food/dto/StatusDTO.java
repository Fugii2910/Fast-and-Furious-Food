package br.fugii.eti.Fast_and_Furious_Food.dto;

import br.fugii.eti.Fast_and_Furious_Food.domain.model.StatusPedido;


public class StatusDTO {
    
    private StatusPedido status;
    
    public StatusPedido getStatus(){
        return status;
    }
    
    public void setStatus(StatusPedido status){
        this.status = status;
    }

}
