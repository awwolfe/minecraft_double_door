package doubledoor.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class BlockDoubleDoor extends BlockDoor {
	



	public BlockDoubleDoor( Material materialIn,float hardness, SoundType sound){
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, Boolean.valueOf(false)).withProperty(HINGE, BlockDoor.EnumHingePosition.LEFT).withProperty(POWERED, Boolean.valueOf(false)).withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER));
        
        setHardness(hardness);
        setSoundType(sound);
        
        
	}
	
	    

	    public BlockDoubleDoor(String name,Material materialIn)
	    {
	        super(materialIn);
	        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, Boolean.valueOf(false)).withProperty(HINGE, BlockDoor.EnumHingePosition.LEFT).withProperty(POWERED, Boolean.valueOf(false)).withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER));
	        setUnlocalizedName(name);
			//setRegistryName(name);
	    }

	   
	   
	   
	    public IBlockState checkDoubleDoor(World worldIn, BlockPos pos,IBlockState state,boolean reverse){
	    	//System.out.println("DoubleDoor check Double Door");
	    	
	    	EnumHingePosition hinge=state.getActualState(worldIn, pos).getValue(HINGE);
	    	EnumFacing facing=getFacing(worldIn,pos);// state.getValue(FACING);
	    	//System.out.println("I'm facing:"+facing);
	    	//System.out.println("My hinge is on the "+hinge);
	    	BlockPos checkPos=null;
	    	
	    	switch(facing){
	    	case NORTH:
	    		checkPos =(hinge==EnumHingePosition.RIGHT) ? pos.west(): pos.east(); 
	    		break;
	    	case SOUTH:
	    		checkPos =(hinge==EnumHingePosition.LEFT) ? pos.west(): pos.east();
	    	break;
	    	case WEST:
	    		checkPos =(hinge==EnumHingePosition.RIGHT) ? pos.south(): pos.north();
	    		break;
	    	case EAST:
	    		checkPos =(hinge==EnumHingePosition.LEFT) ? pos.south(): pos.north();
	    		break;
	    		
	    	}
	    	
	    	IBlockState check_state=worldIn.getBlockState(checkPos);
	    	EnumFacing check_facing=getFacing(worldIn,checkPos);
	    	
	    	if(check_state!=null){
	    		//System.out.println("Check for double door at postion: "+check_state.getBlock().getRegistryName());
	    		
	    		if(check_state.getBlock() instanceof BlockDoubleDoor){
	    			//System.out.println("it is a double door");
	    			
	    			//System.out.println("check is facing:"+check_facing);
	    			EnumHingePosition check_hinge=check_state.getActualState(worldIn, checkPos).getValue(HINGE);
	    			//System.out.println("Check hinge is on the "+check_hinge);
	    			//now check. needs to have the same facing but opposit hinge
	    			if(check_facing==facing)
	    			{
	    			//	System.out.println("Doors facing the same way");
	    				if(check_hinge!=hinge){
	    				//	System.out.println("Hinges are opposite, ok to toggle door");
	    					//sync
	    					boolean open=state.getValue(OPEN);
	    					//worldIn.setBlockState(checkPos, check_state,10);//.withProperty(OPEN, open));
	    					 //worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
	    		              // worldIn.playEvent(playerIn, ((Boolean)state.getValue(OPEN)).booleanValue() ? this.getOpenSound() : this.getCloseSound(), pos, 0);
	    					
	    					//if manually activated, the state is that of after, if used by control, state is of before
	    					boolean op=(reverse) ? !open:open;	
	    					((BlockDoubleDoor)check_state.getBlock()).toggleDoor(worldIn, checkPos, op);//reverse
	    				}
	    			}
	    		}
	    	}
	    	
	    	return check_state;
	    }
	   
	    

	    /* (non-Javadoc)
		 * @see net.minecraft.block.Block#onBlockAdded(net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.state.IBlockState)
		 */
		@Override
		public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
			// TODO Auto-generated method stub
			super.onBlockAdded(worldIn, pos, state);
			System.out.println("DoubleDoor onBlockAdded Double Door");
		}




		/* (non-Javadoc)
		 * @see net.minecraft.block.Block#observedNeighborChange(net.minecraft.block.state.IBlockState, net.minecraft.world.World, net.minecraft.util.math.BlockPos, net.minecraft.block.Block, net.minecraft.util.math.BlockPos)
		 */
		@Override
		public void observedNeighborChange(IBlockState observerState,
				World world, BlockPos observerPos, Block changedBlock,
				BlockPos changedBlockPos) {
			// TODO Auto-generated method stub
			super.observedNeighborChange(observerState, world, observerPos, changedBlock,
					changedBlockPos);
			//System.out.println("DoubleDoor Neighborchange");
		}




		/* (non-Javadoc)
		 * @see net.minecraft.block.Block#setSoundType(net.minecraft.block.SoundType)
		 */
		@Override
		protected Block setSoundType(SoundType sound) {
			// TODO Auto-generated method stub
			return super.setSoundType(sound);
		}




		/**
	     * Called when the block is right clicked by a player.
	     */
	    @Override
	    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {
	    	
	    	//System.out.println("Player activated on face:"+facing);
	    	//for testing allow iron doors to be opened
//	        if (this.blockMaterial == Material.IRON)
//	        {
//	            return false; //Allow items to interact with the door
//	        }
//	        else
//	        {
	            BlockPos blockpos = state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
	            IBlockState iblockstate = pos.equals(blockpos) ? state : worldIn.getBlockState(blockpos);

	            if (iblockstate.getBlock() != this)
	            {
	                return false;
	            }
	            else
	            {
	                state = iblockstate.cycleProperty(OPEN);
	                worldIn.setBlockState(blockpos, state, 10);
	                worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
	                worldIn.playEvent(playerIn, ((Boolean)state.getValue(OPEN)).booleanValue() ? this.getOpenSound() : this.getCloseSound(), pos, 0);
	                checkDoubleDoor(worldIn,blockpos,iblockstate,true);
	                
	                return true;
	            }
	        //}
	    }

	    public void toggleDoor(World worldIn, BlockPos pos, boolean open)
	    {
	        IBlockState iblockstate = worldIn.getBlockState(pos);

	        if (iblockstate.getBlock() == this)
	        {
	            BlockPos blockpos = iblockstate.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
	            IBlockState iblockstate1 = pos == blockpos ? iblockstate : worldIn.getBlockState(blockpos);

	            if (iblockstate1.getBlock() == this && ((Boolean)iblockstate1.getValue(OPEN)).booleanValue() != open)
	            {
	                worldIn.setBlockState(blockpos, iblockstate1.withProperty(OPEN, Boolean.valueOf(open)), 10);
	                worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
	                worldIn.playEvent((EntityPlayer)null, open ? this.getOpenSound() : this.getCloseSound(), pos, 0);
	                
	            }
	        }
	    }

	    /**
	     * Called when a neighboring block was changed and marks that this state should perform any checks during a neighbor
	     * change. Cases may include when redstone power is updated, cactus blocks popping off due to a neighboring solid
	     * block, etc.
	     */
	    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	    {
	        if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER)
	        {
	            BlockPos blockpos = pos.down();
	            IBlockState iblockstate = worldIn.getBlockState(blockpos);

	            if (iblockstate.getBlock() != this)
	            {
	                worldIn.setBlockToAir(pos);
	            }
	            else if (blockIn != this)
	            {
	                iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
	            }
	        }
	        else
	        {
	            boolean flag1 = false;
	            BlockPos blockpos1 = pos.up();
	            IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

	            if (iblockstate1.getBlock() != this)
	            {
	                worldIn.setBlockToAir(pos);
	                flag1 = true;
	            }

	            if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn,  pos.down(), EnumFacing.UP))
	            {
	                worldIn.setBlockToAir(pos);
	                flag1 = true;

	                if (iblockstate1.getBlock() == this)
	                {
	                    worldIn.setBlockToAir(blockpos1);
	                }
	            }

	            if (flag1)
	            {
	                if (!worldIn.isRemote)
	                {
	                    this.dropBlockAsItem(worldIn, pos, state, 0);
	                }
	            }
	            else
	            {
	                boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos1);

	                if (blockIn != this && (flag || blockIn.getDefaultState().canProvidePower()) && flag != ((Boolean)iblockstate1.getValue(POWERED)).booleanValue())
	                {
	                    worldIn.setBlockState(blockpos1, iblockstate1.withProperty(POWERED, Boolean.valueOf(flag)), 2);

	                    if (flag != ((Boolean)state.getValue(OPEN)).booleanValue())
	                    {
	                        worldIn.setBlockState(pos, state.withProperty(OPEN, Boolean.valueOf(flag)), 2);
	                        worldIn.markBlockRangeForRenderUpdate(pos, pos);
	                        worldIn.playEvent((EntityPlayer)null, flag ? this.getOpenSound() : this.getCloseSound(), pos, 0);
	                       // System.out.println("Control open");
	                        checkDoubleDoor(worldIn,pos,worldIn.getBlockState(pos),false);
	                    }
	                }
	            }
	        }
	    }

	    

	    private int getCloseSound()
	    {
	        return this.blockMaterial == Material.IRON ? 1011 : 1012;
	    }

	    private int getOpenSound()
	    {
	        return this.blockMaterial == Material.IRON ? 1005 : 1006;
	    }	
}
