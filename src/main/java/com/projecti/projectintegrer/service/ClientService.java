package com.projecti.projectintegrer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projecti.projectintegrer.domain.dto.ClientDto;
import com.projecti.projectintegrer.domain.entities.Client;
import com.projecti.projectintegrer.exception.MessageEnum;
import com.projecti.projectintegrer.exception.ReservException;
import com.projecti.projectintegrer.mapper.ClientMapper;
import com.projecti.projectintegrer.repositories.ClientRepository;

@Service
public record ClientService(
    ClientRepository clientRepository,
    ClientMapper mapper
) {
     // Create
    public void createClient(ClientDto clientDto){
        Client client = mapper.toEntity(clientDto);
        clientRepository.save(client);
    }

    // Update
    public void updateClient(Integer id, ClientDto clientDto) throws ReservException {
        clientRepository.findById(id)
            .orElseThrow(()-> new ReservException(MessageEnum.DATA_NOT_FOUND));
        Client client = mapper.toEntity(clientDto);
        clientRepository.save(client);
    }

    // Delete
    public void deleteClient(Integer Id) throws ReservException {
        Client client = clientRepository.findById(Id)
        .orElseThrow(()-> new ReservException(MessageEnum.DATA_NOT_FOUND));
        clientRepository.delete(client);
    }

    //getters
    public ClientDto getClientById (Integer Id) throws ReservException {
        Client client = clientRepository.findById(Id)
            .orElseThrow(()-> new ReservException(MessageEnum.DATA_NOT_FOUND));
        return mapper.toDto(client);
    }

    public List<ClientDto> getAllClients(Integer offset, Integer limit) throws ReservException {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Client> reservations = clientRepository.findAll(pageable);
        if (reservations.getContent().isEmpty()){
            throw new ReservException(MessageEnum.DATA_NOT_FOUND);
        }
        return mapper.toDtoList(reservations.getContent());
    }

}

