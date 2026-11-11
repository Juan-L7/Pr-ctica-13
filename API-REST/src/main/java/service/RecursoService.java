package service;

import model.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RecursoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    public Recurso guardarRecurso(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    public List<Recurso> listarRecursos() {
        return recursoRepository.findAll();
    }

    public Optional<Recurso> buscarPorId(Long id) {
        return recursoRepository.findById(id);
    }

    public boolean eliminarRecurso(Long id) {
        if (recursoRepository.findById(id).isPresent()) {
            recursoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Recurso> actualizarParcial(Long id, Recurso datos) {
        return recursoRepository.findById(id).map(recursoExistente -> {
            if (datos.getTitulo() != null) recursoExistente.setTitulo(datos.getTitulo());
            if (datos.getUrl() != null) recursoExistente.setUrl(datos.getUrl());
            if (datos.getTipo() != null) recursoExistente.setTipo(datos.getTipo());
            if (datos.getCategoria() != null) recursoExistente.setCategoria(datos.getCategoria());
            return recursoRepository.save(recursoExistente);
        });
    }

}
