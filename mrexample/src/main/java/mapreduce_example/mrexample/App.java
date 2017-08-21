package mapreduce_example.mrexample;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Hello world!
 *
 */
public class App extends Configured implements Tool 
{
    public static void main( String[] args ) throws Exception
    {
    	Configuration conf = new Configuration();
    	String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
    	ToolRunner.run(new App(), otherArgs);
        
    }

	public int run(String[] args) throws Exception {

		System.out.println("Begin example...");
		
		if(args.length != 2)
		{
			System.err.println("Only 2 parameters are requiered : input file and output file");
			System.exit(-1);
		}
		
		final Path output = new Path(args[1]);
		FileSystem.get(output.toUri(),getConf()).delete(output, true);
		
		Job job = Job.getInstance(new Configuration());
		
		job.setJarByClass(App.class);
		
		
		job.setMapperClass(MyMapper.class);
		job.setReducerClass(MyReduce.class);
	
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DoubleWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
		System.out.println("Por setear los paths... P1: " + args[0] + " P2: " +args[1]);
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		System.out.println("Fin seteo de los paths...");
		
		
		job.waitForCompletion(true);
		
		System.out.println("End of example...");
		
		
		return 0;
	}
}
