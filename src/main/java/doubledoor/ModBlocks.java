package doubledoor;


import doubledoor.block.BlockDoubleDoor;


import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static BlockDoubleDoor doubleDoor;
	public static BlockDoubleDoor oakDoor;
	public static BlockDoubleDoor darkOakDoor;
	public static BlockDoubleDoor spruceDoor;
	public static BlockDoubleDoor jungleDoor;
	public static BlockDoubleDoor birchDoor;
	public static BlockDoubleDoor acaciaDoor;
	
	
	public static void init(){
		doubleDoor=new BlockDoubleDoor(Material.IRON,5.0F, SoundType.METAL); 
    	oakDoor=new BlockDoubleDoor(Material.WOOD,3.0F,SoundType.WOOD);
    	darkOakDoor=new BlockDoubleDoor(Material.WOOD,3.0F,SoundType.WOOD);
    	spruceDoor=new BlockDoubleDoor(Material.WOOD,3.0F,SoundType.WOOD);
    	jungleDoor=new BlockDoubleDoor(Material.WOOD,3.0F,SoundType.WOOD);
    	birchDoor=new BlockDoubleDoor(Material.WOOD,3.0F,SoundType.WOOD);
    	acaciaDoor=new BlockDoubleDoor(Material.WOOD,3.0F,SoundType.WOOD);
    	
    	substitute(Blocks.IRON_DOOR,doubleDoor,"iron_door");
    	substitute(Blocks.OAK_DOOR,oakDoor,"wooden_door");
    	substitute(Blocks.DARK_OAK_DOOR,darkOakDoor,"dark_oak_door");
    	substitute(Blocks.SPRUCE_DOOR,spruceDoor,"spruce_door");
    	substitute(Blocks.JUNGLE_DOOR,jungleDoor,"jungle_door");
    	substitute(Blocks.BIRCH_DOOR,birchDoor,"birch_door");
    	substitute(Blocks.ACACIA_DOOR,acaciaDoor,"acacia_door");
	}
	
	
	public static void substitute(Block toReplace, Block newBlock,String useName) {
		if(newBlock instanceof BlockDoor){
			substitute(toReplace, newBlock, new ItemDoor(newBlock),useName);
		}
		else{
			substitute(toReplace, newBlock, new ItemBlock(newBlock),useName);
		}
	}
	


	public static void substitute(Block toReplace, Block newBlock, Item newItem,String useName) {
		try {
			ResourceLocation oldName = Block.REGISTRY.getNameForObject(toReplace);
			String nameToSubstitute = oldName.toString();

			String nameToRegister = DoubleDoorMod.MODID + ":" + useName;
			System.out.println("Substitute:"+nameToSubstitute+" with "+nameToRegister);
			
			newBlock.setRegistryName(nameToRegister);
			newBlock.setUnlocalizedName(useName);
			GameRegistry.addSubstitutionAlias(nameToSubstitute.toString(), GameRegistry.Type.BLOCK, newBlock);
			
			newItem.setRegistryName(nameToRegister);
			newItem.setUnlocalizedName(useName);
			GameRegistry.addSubstitutionAlias(nameToSubstitute.toString(), GameRegistry.Type.ITEM, newItem);
		} catch (ExistingSubstitutionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
