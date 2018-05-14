package ru.job4j.dao.Impl;


import lombok.extern.slf4j.Slf4j;
import ru.job4j.dao.GenericDao;
import ru.job4j.model.Comment;

/**
 * TODO comments
 *
 * @author Pyotr Kukharenka
 * @since 04.04.2018
 */

@Slf4j
public class CommentDao extends GenericDao<Comment, Long> {

    protected CommentDao() {
        super(Comment.class);
    }
}
