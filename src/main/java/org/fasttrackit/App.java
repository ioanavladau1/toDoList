package org.fasttrackit;

import org.fasttrackit.persistance.ToDoItemRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, IOException, ClassNotFoundException {
        ToDoItemRepository toDoItemRepository = new ToDoItemRepository();
//        toDoItemRepository.createToDoItem("Learn Java",
//                LocalDateTime.now().plusMonths(6));

//        toDoItemRepository.deteteToDoItem(1);

        System.out.println(toDoItemRepository.getToDoItems());

        toDoItemRepository.updateToDoItem(2,true);

        System.out.println(toDoItemRepository.getToDoItems());
    }
}
