package Connectia.dao;



import jakarta.enterprise.context.ApplicationScoped;
import Connectia.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsuarioDao implements PanacheRepository<Usuario> {

    public Usuario buscarPorId(Long id) {
        return find("id", id).firstResult();
    }

    public Usuario buscarPorEmail(String email) {
        return find("email", email).firstResult();
    }

    public void salvar(Usuario usuario) {
        persist(usuario);
    }

    public boolean deletar(Long id) {
        Usuario u = buscarPorId(id);
        if (u != null) {
            delete(u);
            return true;
        }
        return false;
    }
}
