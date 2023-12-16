package com.example.projet.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long idt;
    private Long idVo;
    private Long idCl;
}
