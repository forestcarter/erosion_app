/**----------------------------------------------------------------------------------
* Microsoft Developer & Platform Evangelism
*
* Copyright (c) Microsoft Corporation. All rights reserved.
*
* THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND,
* EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES
* OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.
*----------------------------------------------------------------------------------
* The example companies, organizations, products, domain names,
* e-mail addresses, logos, people, places, and events depicted
* herein are fictitious.  No association with any real company,
* organization, product, domain name, email address, logo, person,
* places, or events is intended or should be inferred.
*----------------------------------------------------------------------------------
**/

package com.donsaguaro.keetseelerosion;

import android.app.Activity;
import android.content.Context;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.LinkedList;

public class ImageManager {
    /*
    **Only use Shared Key authentication for testing purposes!** 
    Your account name and account key, which give full read/write access to the associated Storage account, 
    will be distributed to every person that downloads your app. 
    This is **not** a good practice as you risk having your key compromised by untrusted clients. 
    Please consult following documents to understand and use Shared Access Signatures instead. 
    https://docs.microsoft.com/en-us/rest/api/storageservices/delegating-access-with-a-shared-access-signature 
    and https://docs.microsoft.com/en-us/azure/storage/common/storage-dotnet-shared-access-signature-part-1 
    */


    public static final String storageConnectionString = "BlobEndpoint=https://keetseelerosion.blob.core.windows.net/;" +
            "TableEndpoint=https://keetseelerosion.table.core.windows.net/;" +
            "SharedAccessSignature=sv=2017-11-09&ss=b&srt=sco&sp=rwlac&se=2028-06-21T04:15:26Z&st=2018-06-20T20:15:26Z&spr=https,http&sig=";


//    public static final String storageConnectionString = "BlobEndpoint=https://keetseelerosion.blob.core.windows.net/;" +
//            "TableEndpoint=https://keetseelerosion.table.core.windows.net/;" +
//            "SharedAccessSignature=sv=2017-11-09&ss=b&srt=sco&sp=rwlac&se=2028-06-21T04:15:26Z&st=2018-06-20T20:15:26Z&spr=https,http&sig=YvS15O5T%2BiS6dOFnt7b10GvOAtKLaTsNSV0WzMcD6YA%3D";

    private static CloudBlobContainer getContainer(String storageSig) throws Exception {
        // Retrieve storage account from connection-string.
        //String privacyText = getString(R.string.privacyText);
        String finalString = storageConnectionString + storageSig;
        System.out.println("Final String is " +finalString);

        CloudStorageAccount storageAccount = CloudStorageAccount
                .parse(finalString);

        // Create the blob client.
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

        // Get a reference to a container.
        // The container name must be lower case
        CloudBlobContainer container = blobClient.getContainerReference("images");

        return container;
    }

    public static String UploadImage(InputStream image, int imageLength, String dateStringFinal, int myLoc, String storageSig) throws Exception {
        String imageName = myLoc + dateStringFinal;
        System.out.println("image name " + imageName);


        CloudBlobContainer container = getContainer(storageSig);

        container.createIfNotExists();
        //String imageName = locationNum.toSring()+year+month+day;
        //String imageName = randomString(10);


        CloudBlockBlob imageBlob = container.getBlockBlobReference(imageName);
        imageBlob.upload(image, imageLength);

        return imageName;

    }
}
//    public static String[] ListImages() throws Exception{
//        CloudBlobContainer container = getContainer();
//
//        Iterable<ListBlobItem> blobs = container.listBlobs();
//
//        LinkedList<String> blobNames = new LinkedList<>();
//        for(ListBlobItem blob: blobs) {
//            blobNames.add(((CloudBlockBlob) blob).getName());
//        }
//
//        return blobNames.toArray(new String[blobNames.size()]);
//    }

//
//public static void GetImage(String name, OutputStream imageStream, long imageLength) throws Exception {
//        CloudBlobContainer container = getContainer();
//
//        CloudBlockBlob blob = container.getBlockBlobReference(name);
//
//        if(blob.exists()){
//            blob.downloadAttributes();
//
//            imageLength = blob.getProperties().getLength();
//
//            blob.download(imageStream);
//        }
//    }
//    static final String validChars = "abcdefghijklmnopqrstuvwxyz";
//    static SecureRandom rnd = new SecureRandom();
//
//    static String randomString( int len ){
//        StringBuilder sb = new StringBuilder( len );
//        for( int i = 0; i < len; i++ )
//            sb.append( validChars.charAt( rnd.nextInt(validChars.length()) ) );
//        return sb.toString();
//    }
//
//}
