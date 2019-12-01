package com.example.autobill;

import java.util.List;

public class FriendsBeanList {

    private List<TodosBean> todos;

    public List<TodosBean> getTodos() {
        return todos;
    }

    public void setTodos(List<TodosBean> todos) {
        this.todos = todos;
    }

    public static class TodosBean {
        /**
         * id : 1
         * title : Todo 1
         * completed : false
         */

        private int id;
        private String title;
        private boolean completed;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }
}
