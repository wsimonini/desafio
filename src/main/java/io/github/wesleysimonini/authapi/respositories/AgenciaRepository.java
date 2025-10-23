package io.github.wesleysimonini.authapi.respositories;

import io.github.wesleysimonini.authapi.models.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {}