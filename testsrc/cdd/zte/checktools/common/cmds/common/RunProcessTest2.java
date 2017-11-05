package cdd.zte.checktools.common.cmds.common;

import java.io.IOException;


public class RunProcessTest2
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        String cmd = "ipconfig /all";

        int count = 0;
        while(true)
        {
            CmdRunResult result = RunProcess.runDosCommand(format(cmd));
            count++;
            if(count % 10 == 0)
            {
                System.out.println(count);
            }
        }
    }

    private static String[] format(String cmd)
    {
        return cmd.split(" ");
    }
}
