package pck;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

public class Main
{
	public static String HEADER =
		"ruler_designer_1560552532=\n" + 
		"{\n" + 
		"	type=?\n" + 
		"	id=0\n" + 
		"	genes=\n" + 
		"	{\n";
	
	public static String[] GENES = new String[]
	{
		"hair_color",
		"skin_color",
		"eye_color",
		"gene_chin_forward",
		"gene_chin_height",
		"gene_chin_width",
		"gene_eye_angle",
		"gene_eye_depth",
		"gene_eye_height",
		"gene_eye_distance",
		"gene_eye_shut",
		"gene_forehead_angle",
		"gene_forehead_brow_height",
		"gene_forehead_roundness",
		"gene_forehead_width",
		"gene_forehead_height",
		"gene_head_height",
		"gene_head_width",
		"gene_head_profile",
		"gene_head_top_height",
		"gene_head_top_width",
		"gene_jaw_angle",
		"gene_jaw_forward",
		"gene_jaw_height",
		"gene_jaw_width",
		"gene_mouth_corner_depth",
		"gene_mouth_corner_height",
		"gene_mouth_forward",
		"gene_mouth_height",
		"gene_mouth_width",
		"gene_mouth_upper_lip_size",
		"gene_mouth_lower_lip_size",
		"gene_mouth_open",
		"gene_neck_length",
		"gene_neck_width",
		"gene_bs_cheek_forward",
		"gene_bs_cheek_height",
		"gene_bs_cheek_width",
		"gene_bs_ear_angle",
		"gene_bs_ear_inner_shape",
		"gene_bs_ear_bend",
		"gene_bs_ear_outward",
		"gene_bs_ear_size",
		"gene_bs_eye_corner_depth",
		"gene_bs_eye_fold_shape",
		"gene_bs_eye_size",
		"gene_bs_eye_upper_lid_size",
		"gene_bs_forehead_brow_curve",
		"gene_bs_forehead_brow_forward",
		"gene_bs_forehead_brow_inner_height",
		"gene_bs_forehead_brow_outer_height",
		"gene_bs_forehead_brow_width",
		"gene_bs_jaw_def",
		"gene_bs_mouth_lower_lip_def",
		"gene_bs_mouth_lower_lip_full",
		"gene_bs_mouth_lower_lip_pad",
		"gene_bs_mouth_lower_lip_width",
		"gene_bs_mouth_philtrum_def",
		"gene_bs_mouth_philtrum_shape",
		"gene_bs_mouth_philtrum_width",
		"gene_bs_mouth_upper_lip_def",
		"gene_bs_mouth_upper_lip_full",
		"gene_bs_mouth_upper_lip_profile",
		"gene_bs_mouth_upper_lip_width",
		"gene_bs_nose_forward",
		"gene_bs_nose_height",
		"gene_bs_nose_length",
		"gene_bs_nose_nostril_height",
		"gene_bs_nose_nostril_width",
		"gene_bs_nose_profile",
		"gene_bs_nose_ridge_angle",
		"gene_bs_nose_ridge_width",
		"gene_bs_nose_size",
		"gene_bs_nose_tip_angle",
		"gene_bs_nose_tip_forward",
		"gene_bs_nose_tip_width",
		"face_detail_cheek_def",
		"face_detail_cheek_fat",
		"face_detail_chin_cleft",
		"face_detail_chin_def",
		"face_detail_eye_lower_lid_def",
		"face_detail_eye_socket",
		"face_detail_nasolabial",
		"face_detail_nose_ridge_def",
		"face_detail_nose_tip_def",
		"face_detail_temple_def",
		"expression_brow_wrinkles",
		"expression_eye_wrinkles",
		"expression_forehead_wrinkles",
		"expression_other",
		"complexion",
		"gene_height",
		"gene_bs_body_type",
		"gene_bs_body_shape",
		"gene_bs_bust",
		"gene_age",
		"gene_eyebrows_shape",
		"gene_eyebrows_fullness",
		"gene_body_hair",
		"gene_hair_type",
		"gene_baldness",
		"eye_accessory",
		"teeth_accessory",
		"eyelashes_accessory",
		"hairstyles"
	};
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Input character sex (m/f): ");
		
		String sex;
		
		do
		{
			sex = bf.readLine();
		}
		while(!sex.toLowerCase().equals("m") && !sex.toLowerCase().equals("f"));
			
		System.out.println("Input character encripted code:\n");
		
		String encripted = "";
		
		do
		{
			encripted = bf.readLine();
		}
		while(encripted.equals(""));
		
		byte[] decodedBytes = Base64.getDecoder().decode(encripted);
		
		for(int i = 0, l = decodedBytes.length; i < l; )
			System.out.println((decodedBytes[i++] & 0xFF) + " " + (decodedBytes[i++] & 0xFF) + " " + (decodedBytes[i++] & 0xFF) + " " + (decodedBytes[i++] & 0xFF));
		
		System.out.println("\nDecripted code:\n");
		
		String designerDNA;
		
		int index = HEADER.indexOf("?");
		designerDNA = HEADER.substring(0, index) + (sex.toLowerCase().equals("m") ? "male" : "female") + HEADER.substring(index + 1);
		
		for(int i = 0, j = 0, l = GENES.length, k = decodedBytes.length; i < l && j < k; i++)
			designerDNA += "		" + GENES[i] + "={ " + (decodedBytes[j++] & 0xFF) + " " + (decodedBytes[j++] & 0xFF) + " " + (decodedBytes[j++] & 0xFF) + " " + (decodedBytes[j++] & 0xFF) + " }\n";
		
		designerDNA += "	}\n}";
		
		System.out.println(designerDNA);	
		
		StringSelection stringSelection = new StringSelection(designerDNA);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		System.out.println("\nRuler Designer DNA has been copied to the clipboard!");
	}
}
