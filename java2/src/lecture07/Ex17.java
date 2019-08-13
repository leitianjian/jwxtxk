package lecture07;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

class T1  {
   public int value;
   public int hashCode (){
      return 42;
   }
}

class T2 {
   public int value;

   public int hashCode (){
      return (value ^ 5);
   }
}
public class Ex17 {
   public static void main(String[] args) throws IOException
   {
//      List<Movie> movieList = Movies.readMovies("movies.txt");
//      long startTime = System.currentTimeMillis();
//      // method 1:
////      Set<String> actors = new HashSet<>();
////      movieList.forEach(i -> actors.addAll(i.getActors()));
//////      System.out.println(actors.size());
////      long i = actors.size();
//      long i = movieList.stream()
//              .collect(HashSet::new, (t, e) -> t.addAll(e.getActors()), HashSet::addAll)
//              .size();
//      long endTime = System.currentTimeMillis();
//      System.out.println(endTime - startTime);
//      System.out.println(i);

//      int value = 100;
      FileOutputStream out = null;
      try {
         out = new FileOutputStream("test.txt");
         out.write(122);
      } catch (IOException e){
         System.out.println("IO error.");
      } finally {
         out.close();
      }
   }
}
