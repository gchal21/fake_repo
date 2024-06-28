package DAOclasses.DAOinterfaces;

import entities.Tag;

import java.util.List;

public interface TagDAO {
    Tag getById(Integer id);
    List<Tag> getAll();
    void insert(Tag tag);
    void update(Tag tag);
    void delete(Integer id);
}
