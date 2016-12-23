package com.example.walle.ffnntest;

import android.util.Log;

/**
 * Created by walle on 12/21/2016.
 */

public class FFNN {
    int numLayers;
    int[] sizes;
    double[][] biases;
    double[][][] weights;

    public FFNN(int[] sizes){
        numLayers = sizes.length;
        this.sizes = sizes;

        biases = new double[numLayers-1][];
        for(int i =1; i< sizes.length; i++){
            int y = sizes[i];
            biases[i-1] = new double[y];
        }

        weights = new double[numLayers-1][][];
        for(int i = 0,j=1; i<sizes.length-1; i++, j++){
            int x = sizes[i];
            int y = sizes[j];
            weights[i] = new double[y][x];
        }

    }

    public double[] feedforward(double[] a){
        if(a.length != sizes[0]){
            Log.e("walter's tag", "FFNN:feedforward: given array size does not match size of first layer");
            return null;
        }
        for(int i = 0; i< weights.length; i++){
            a = dot(weights[i], a);
            a = plus(a, biases[i]);
        }
        return a;
    }

    public void backprop(double[] input, double[] output){

    }

    public static double[] dot(double[][] mat, double[]vector){
        if(mat[0].length!= vector.length){
            Log.e("walter's tag", "FFNN:dot: array sizes dont match");
            return null;
        }
        double[] result = new double[mat.length];
        for(int r = 0; r< mat.length; r++){
            result[r] = dot(mat[r], vector);
        }
        return result;
    }

    public static double dot(double[] vec1, double[] vec2){
        if(vec1.length != vec2.length){
            Log.e("walter's tag", "FFNN:dot: array sizes dont match");
            return 0;
        }
        double result = 0;
        for(int i = 0 ; i< vec1.length; i++){
            result += vec1[i]*vec2[i];
        }
        return result;
    }

    public static double[] plus(double[] vec1, double[] vec2){
        if(vec1.length != vec2.length){
            Log.e("walter's tag", "FFNN:plus: array sizes dont match");
            return null;
        }
        double[] result = new double[vec1.length];
        for(int i = 0; i< vec1.length; i++){
            result[i] = vec1[i]+vec2[i];
        }
        return result;
    }

    public double sigmoid(double z){
        return 1.0/(1.0+Math.exp(-z));
    }
    public double sigmoidPrime(double z){
        return sigmoid(z)*(1-sigmoid(z));
    }
}
