package com.example.project;

import java.util.ArrayList;

public class GraphMatrix implements Graph {

    private int numVertices;
    private int[][] adjacency;

    public GraphMatrix(int numVertices) {
        this.numVertices = numVertices;
        this.adjacency = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                this.adjacency[i][j] = 0;
            }
        }
    }

    @Override
    public boolean addEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            this.adjacency[from][to] = 1;
            this.adjacency[to][from] = 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(int from, int to) {
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            this.adjacency[from][to] = 0;
            this.adjacency[to][from] = 0;
            return true;
        }
        return false;
    }

    public boolean vertexDoesExist(int aVertex) {
        if (aVertex >= 0 && aVertex < this.numVertices) {
            return true;
        } else {
            return false;
        }
    }

    public boolean findEdge(int from, int to){
        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
            if(this.adjacency[from][to]==1){
                return true;
            }
            
        }
        return false;
    }

    public ArrayList<Integer> depthFirstSearch(int n) {
        return this.depthFirstSearch(n, new ArrayList<Integer>());
    }

    public ArrayList<Integer> depthFirstSearch(int n, ArrayList<Integer> visited) {
        visited.add(n);
        for (int i = 0; i < this.numVertices; i++) {
            if (this.adjacency[n][i] == 1 && !visited.contains(i)) {
                depthFirstSearch(i, visited);
            }
        }
        return visited;
    }

    public String toString() {
        String s = "    ";
        for (int i = 0; i < this.numVertices; i++) {
            s = s + String.valueOf(i) + " ";
        }
        s = s + " \n";

        for (int i = 0; i < this.numVertices; i++) {
            s = s + String.valueOf(i) + " : ";
            for (int j = 0; j < this.numVertices; j++) {
                s = s + String.valueOf(this.adjacency[i][j]) + " ";
            }
            s = s + "\n";
        }
        return s;
    }

    public int countConnectedComponents() {
        

        ArrayList<ArrayList<Integer>> conectados = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> faltantes = new ArrayList<Integer>();
        
        for (int i = 0;i<this.numVertices;i++){
            faltantes.add(i);
        }

        do {
            conexiones(conectados, faltantes);
            
        } while(faltantes.size() != 0);    
        

        
        return conectados.size();
    }
    //trabaja con una coleccion donde esta otra coleccion que guardan los vertices que estan conectados
    public void conexiones(ArrayList<ArrayList<Integer>> conectados, ArrayList<Integer> faltantes){
        
        
        //crea el array que guarda al grupo correspondiente.
        ArrayList<Integer> grafoConexo = new ArrayList<Integer>();

        grafoConexo.add(faltantes.get(0));
        faltantes.remove(0);


        //compara los datos faltantes con los que ya fueron emparejados
        for(int i = 0; i<faltantes.size();i++){
            if(comparar(grafoConexo,faltantes.get(i))){
                
                faltantes.remove(i);
                i--;
            }
            
        }

        //añade el arraylist al contenedor, que devolvera la cantidad de arrays existentes.
        conectados.add(grafoConexo);

               
    }
    // se creo este método para actualizar el arraylist en cada iteración
    public boolean comparar(ArrayList<Integer> grafo, int indice){

        ArrayList<Integer> grafoConexo = grafo;

        for (int i = 0; i<grafoConexo.size();i++){
            if(findEdge(indice, grafoConexo.get(i))){
                grafoConexo.add(indice);
                return true;
            }
        }

        return false;
    }

    

    public static void main(String args[]) {
        GraphMatrix graph = new GraphMatrix(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        System.out.println("The graph matrix:");
        System.out.println(graph);
        System.out.println("DFS:");
        System.out.println(graph.depthFirstSearch(0));
    }

}
