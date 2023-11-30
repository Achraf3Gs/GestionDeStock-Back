package com.Guesmi.gestion.du.stock.services.impl;


import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


import com.Guesmi.gestion.du.stock.dto.ClientDto;
import com.Guesmi.gestion.du.stock.dto.EntrepriseDto;
import com.Guesmi.gestion.du.stock.dto.RolesDto;
import com.Guesmi.gestion.du.stock.dto.UtilisateurDto;
import com.Guesmi.gestion.du.stock.exception.EntityNotFoundException;
import com.Guesmi.gestion.du.stock.exception.ErrorCodes;
import com.Guesmi.gestion.du.stock.exception.InvalidEntityException;
import com.Guesmi.gestion.du.stock.repository.EntrepriseRepository;
import com.Guesmi.gestion.du.stock.repository.RolesRepository;
import com.Guesmi.gestion.du.stock.services.EntrepriseService;
import com.Guesmi.gestion.du.stock.services.UtilisateurService;
import com.Guesmi.gestion.du.stock.validator.EntrepriseValidator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

  private EntrepriseRepository entrepriseRepository;
  private UtilisateurService utilisateurService;
  private RolesRepository rolesRepository;

  @Autowired
  public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, UtilisateurService utilisateurService,
      RolesRepository rolesRepository) {
    this.entrepriseRepository = entrepriseRepository;
    this.utilisateurService = utilisateurService;
    this.rolesRepository = rolesRepository;
  }

  @Override
  public EntrepriseDto save(EntrepriseDto dto) {
    List<String> errors = EntrepriseValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Entreprise is not valid {}", dto);
      throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
    }
    return EntrepriseDto.fromEntity(
            entrepriseRepository.save(
                    EntrepriseDto.toEntity(dto)
            )
    );
  }

  @Override
  public EntrepriseDto findById(Integer id) {
    if (id == null) {
      log.error("Entreprise ID is null");
      return null;
    }
    return entrepriseRepository.findById(id)
        .map(EntrepriseDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune entreprise avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
  }

  @Override
  public List<EntrepriseDto> findAll() {
    return entrepriseRepository.findAll().stream()
        .map(EntrepriseDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Entreprise ID is null");
      return;
    }
    entrepriseRepository.deleteById(id);
  }
}
