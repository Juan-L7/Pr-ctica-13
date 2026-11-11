package controller;

import model.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.RecursoService;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    @Autowired
    private RecursoService recursoService;

    @PostMapping
    public ResponseEntity<Recurso> crear(@RequestBody Recurso recurso) {
        return new ResponseEntity<>(recursoService.guardarRecurso(recurso), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Recurso>> listar() {
        return ResponseEntity.ok(recursoService.listarRecursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recurso> buscarPorId(@PathVariable Long id) {
        return recursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recurso> actualizar(@PathVariable Long id, @RequestBody Recurso recurso) {
        if (!recursoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        recurso.setId(id);
        return ResponseEntity.ok(recursoService.guardarRecurso(recurso));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Recurso> actualizarParcial(@PathVariable Long id, @RequestBody Recurso datos) {
        return recursoService.actualizarParcial(id, datos)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (recursoService.eliminarRecurso(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
