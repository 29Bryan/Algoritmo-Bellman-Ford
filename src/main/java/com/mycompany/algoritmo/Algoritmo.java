/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.algoritmo;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author PCG
 */
public class Algoritmo {
    
    private LinkedList<Aristas> aristas; 
    private float etiquetas[]; 
    private int predecesor[]; 
    private int numeroVertices, totalAristas, nodoOrigen; 
    private final int INFINITY = 999;
    
    public static class Aristas{
        int origen, destino; 
        float coste; 
        
        public Aristas(int a, int b, float c){
            origen = a; 
            destino = b; 
            coste = c; 
        }

        @Override
        public String toString() {
            return "Aristas{" + "origen=" + origen + ", destino=" + destino + ", coste=" + coste + '}';
        }
    }
    
    public Algoritmo()throws IOException{
        float item; 
        aristas = new LinkedList<Aristas>(); 
        DataInputStream in = new DataInputStream(System.in); 
        System.out.println("Introduce el Numero de Vertices ");
        numeroVertices = Integer.parseInt(in.readLine()); 
        System.out.println("Matriz de Costes");
        for(int i = 0; i < numeroVertices; i++){
            for(int j = 0; j < numeroVertices; j++){
                if(i !=j){
                    System.out.println("Introduce el Coste del Nodo " + (i + 1)+ "al Nodo " + (j + 1));
                    item = Float.parseFloat(in.readLine()); 
                    if(item !=0){
                        aristas.add(new Aristas(i, j, item)); 
                    }
                }
            }
        }
        totalAristas = aristas.size(); 
        etiquetas = new float [numeroVertices];
        predecesor = new int [numeroVertices]; 
        System.out.println("Introduce el Vertice de Origen"); 
        nodoOrigen = Integer.parseInt(in.readLine()) - 1; 
    }
    
    private void relojArista(){
        int i, j; 
        for(i = 0; i<numeroVertices; ++i){
            etiquetas[i] = INFINITY; 
        }
        etiquetas[nodoOrigen] = 0;
        for(i = 0; i < numeroVertices - 1; ++i){
            for(j = 0; j < totalAristas; ++j){
                System.out.println(aristas.get(j));
                if(etiquetas[aristas.get(j).origen] + aristas.get(j).coste < etiquetas[aristas.get(j).destino]){
                    etiquetas[aristas.get(j).destino] = etiquetas[aristas.get(j).origen] + aristas.get(j).coste; 
                    predecesor[aristas.get(j).destino] = aristas.get(j).origen; 
                }
            } 
            for(int p = 0; etiquetas.length < p; p++){
                System.out.println("\t" + etiquetas[p]);
            }
        }
    }
    
    private boolean ciclo(){
        int j; 
        for(j = 0; j<totalAristas; ++j){
            if(etiquetas[aristas.get(j).origen] + aristas.get(j).coste < etiquetas[aristas.get(j).destino]){
                return false; 
            }
        }
        return true; 
    }
    
    public static void main(String[] args) throws IOException{
        Algoritmo BellmanFord = new Algoritmo();
        BellmanFord.relojArista();
        if(BellmanFord.ciclo()){
            for(int i = 0; i < BellmanFord.numeroVertices; i++){
                System.out.println("Coste desde "+ BellmanFord.nodoOrigen+ " a "+ (i + 1)+ " =>"+ BellmanFord.etiquetas[i]);
            }
            for(int i = 0; i < BellmanFord.numeroVertices; i++){
                System.out.println("El Predecesor de " + (i + 1) + " es " + (BellmanFord.predecesor[i] + 1));
            }
        }else{
            System.out.println("Hay un ciclo negativo");
        }
    }
}
