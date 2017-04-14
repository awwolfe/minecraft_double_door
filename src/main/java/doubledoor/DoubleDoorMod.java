package doubledoor;



import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = DoubleDoorMod.MODID, version = DoubleDoorMod.VERSION, name=DoubleDoorMod.NAME)
public class DoubleDoorMod
{
    public static final String MODID = "doubledoormod";
    public static final String VERSION = "1.0";
    public static final String NAME= "Double Door Mod";
    
    @Mod.Instance(MODID)
	public static DoubleDoorMod instance;
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    	System.out.println(DoubleDoorMod.NAME+" loading......");
    	ModBlocks.init();
    	
    		
    }
}
