/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc02.model.bo;

/**
 *
 * @author wcsan_000
 */
public class Geometria {

    public boolean validaIntersecaoLinhas(double p0_x, double p0_y, double p1_x, double p1_y,
            double p2_x, double p2_y, double p3_x, double p3_y) {
        double s10_x = p1_x - p0_x;
        double s10_y = p1_y - p0_y;
        double s32_x = p3_x - p2_x;
        double s32_y = p3_y - p2_y;
        
        double denom = s10_x * s32_y - s32_x * s10_y;
        if (denom == 0)
            return false;
        boolean denomPositive = denom > 0;
        double s02_x = p0_x - p2_x;
        double s02_y = p0_y - p2_y;
        double s_numer = s10_x * s02_y - s10_y * s02_x;
        boolean s_numerNegative = s_numer < 0;
        if (s_numerNegative == denomPositive) { return false; }
        double t_numer = s32_x * s02_y - s32_y * s02_x;
        boolean t_numerNegative = t_numer < 0;
        if (t_numerNegative == denomPositive) { return false; }
        if ( (s_numerNegative == denomPositive) || (t_numerNegative == denomPositive)) { return false; }
        return true;
    } 

}



//int get_line_intersection(float p0_x, float p0_y, float p1_x, float p1_y, 
//    float p2_x, float p2_y, float p3_x, float p3_y, float *i_x, float *i_y)
//{
//    float s02_x, s02_y, s10_x, s10_y, s32_x, s32_y, s_numer, t_numer, denom, t;
//    s10_x = p1_x - p0_x;
//    s10_y = p1_y - p0_y;
//    s32_x = p3_x - p2_x;
//    s32_y = p3_y - p2_y;
//
//    denom = s10_x * s32_y - s32_x * s10_y;
//    if (denom == 0)
//        return 0; // Collinear
//    bool denomPositive = denom > 0;
//
//    s02_x = p0_x - p2_x;
//    s02_y = p0_y - p2_y;
//    s_numer = s10_x * s02_y - s10_y * s02_x;
//    if ((s_numer < 0) == denomPositive)
//        return 0; // No collision
//
//    t_numer = s32_x * s02_y - s32_y * s02_x;
//    if ((t_numer < 0) == denomPositive)
//        return 0; // No collision
//
//    if (((s_numer > denom) == denomPositive) || ((t_numer > denom) == denomPositive))
//        return 0; // No collision
//    // Collision detected
//    t = t_numer / denom;
//    if (i_x != NULL)
//        *i_x = p0_x + (t * s10_x);
//    if (i_y != NULL)
//        *i_y = p0_y + (t * s10_y);
//
//    return 1;
//}

// Returns 1 if the lines intersect, otherwise 0. In addition, if the lines 
// intersect the intersection point may be stored in the floats i_x and i_y.
//char get_line_intersection(float p0_x, float p0_y, float p1_x, float p1_y, 
//    float p2_x, float p2_y, float p3_x, float p3_y, float *i_x, float *i_y)
//{
//    float s1_x, s1_y, s2_x, s2_y;
//    s1_x = p1_x - p0_x;     s1_y = p1_y - p0_y;
//    s2_x = p3_x - p2_x;     s2_y = p3_y - p2_y;
//
//    float s, t;
//    s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / (-s2_x * s1_y + s1_x * s2_y);
//    t = ( s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / (-s2_x * s1_y + s1_x * s2_y);
//
//    if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
//    {
//        // Collision detected
//        if (i_x != NULL)
//            *i_x = p0_x + (t * s1_x);
//        if (i_y != NULL)
//            *i_y = p0_y + (t * s1_y);
//        return 1;
//    }
//
//    return 0; // No collision
//}
