package com.mysports.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
import com.mysports.R;
import com.mysports.activity.ImageVedioActivity;
import com.mysports.adapter.StoreProductListRV;
import com.mysports.adapter.SwipeDataRV;
import com.mysports.bean.StoreProductList;
import com.mysports.utilities.TouchImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StoreDetailFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ViewPager mProductViewpager;
    /*CustomPagerAdapter mCustomPagerAdapter;*/
    int[] mResources;
    RelativeLayout mProductSelection;
    RecyclerView mProductListRecycleView;
    ArrayList<StoreProductList> productLists;
    RelativeLayout mProductMoveLeft;
    RelativeLayout mProductMoveRight;
    LinearLayoutManager productListManager;
    RelativeLayout mSizeLister;
    RelativeLayout mSizeMoveLeft;
    RelativeLayout mSizeMoveRight;
    RecyclerView mSizeRV;
    RelativeLayout mQuantityLister;
    RelativeLayout mQuantityMoveLeft;
    RelativeLayout mQuantityMoveRight;
    RecyclerView mQualityRv;
    String[] sizeList;
    String[] quantityList;
    String[] colorList;
    LinearLayoutManager sizeLayoutmanager;
    SwipeDataRV swipeDataRV;
    LinearLayoutManager qualityLayoutmanager;
    SwipeDataRV swipeDataRVQ;
    ImageView mProductShare;
    ImageView mProductLike;
    Bitmap mShareImageBitmap;
    ImageView currentProductImage;

    public StoreDetailFragment() {
        // Required empty public constructor
    }


    public static StoreDetailFragment newInstance(String param1, String param2) {
        StoreDetailFragment fragment = new StoreDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProductMoveLeft = view.findViewById(R.id.product_move_left);
        mProductMoveLeft.setOnClickListener(this);
        mProductMoveRight = view.findViewById(R.id.product_right_move);
        mProductMoveRight.setOnClickListener(this);
        mResources = new int[]{
                R.drawable.product_image_demo,
                R.drawable.product_image_demo,
                R.drawable.product_image_demo,
        };
        //mProductViewpager = view.findViewById(R.id.store_pager);
       /* mCustomPagerAdapter = new CustomPagerAdapter(getActivity());
        mProductViewpager.setAdapter(mCustomPagerAdapter);*/
        currentProductImage = view.findViewById(R.id.product_image);
        mProductSelection = view.findViewById(R.id.product_selection);
        mProductListRecycleView = mProductSelection.findViewById(R.id.rage_viewpager);

        productListManager = new LinearLayoutManager(getActivity());
        productListManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mProductListRecycleView.setLayoutManager(productListManager);
        storelistProduct();
        StoreProductListRV storeProductListRV = new StoreProductListRV(getActivity(), productLists);
        mProductListRecycleView.setAdapter(storeProductListRV);


        sizequantityData();


        mSizeLister = view.findViewById(R.id.size_list);
        mSizeMoveLeft = mSizeLister.findViewById(R.id.size_move_left);
        mSizeMoveLeft.setOnClickListener(this);
        mSizeMoveRight = mSizeLister.findViewById(R.id.size_move_right);
        mSizeMoveRight.setOnClickListener(this);

        mSizeRV = mSizeLister.findViewById(R.id.size_rage_viewpager);
        sizeLayoutmanager = new LinearLayoutManager(getActivity());
        sizeLayoutmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mSizeRV.setLayoutManager(sizeLayoutmanager);
        swipeDataRV = new SwipeDataRV(getActivity(), sizeList);
        mSizeRV.setAdapter(swipeDataRV);

        mQuantityLister = view.findViewById(R.id.quality_list);
        mQuantityMoveLeft = mQuantityLister.findViewById(R.id.quality_move_left);
        mQuantityMoveLeft.setOnClickListener(this);
        mQuantityMoveRight = mQuantityLister.findViewById(R.id.quality_move_right);
        mQuantityMoveRight.setOnClickListener(this);

        mQualityRv = mQuantityLister.findViewById(R.id.quality_rage_viewpager);
        qualityLayoutmanager = new LinearLayoutManager(getActivity());
        qualityLayoutmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mQualityRv.setLayoutManager(qualityLayoutmanager);
        swipeDataRVQ = new SwipeDataRV(getActivity(), quantityList);
        mQualityRv.setAdapter(swipeDataRVQ);


        mProductShare = view.findViewById(R.id.product_share);
        mProductShare.setOnClickListener(this);

        mProductLike = view.findViewById(R.id.product_like);
        mProductLike.setOnClickListener(this);
    }

    private void sizequantityData() {
        sizeList = new String[]{"7.5", "8", "8.5", "9", "7.5", "8", "8.5", "9", "7.5", "8", "8.5", "9", "7.5", "8", "8.5", "9"};
        quantityList = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        colorList = new String[]{"black", "white"};
    }


    private void storelistProduct() {
        productLists = new ArrayList<>();
        int[] storeproduct = new int[]{R.drawable.demo_product, R.drawable.demo_product, R.drawable.demo_product, R.drawable.demo_product, R.drawable.demo_product};
        //int[] storeproduct = {R.drawable.ic_booking, R.drawable.ic_washroom, R.drawable.ic_parking, R.drawable.ic_equipments, R.drawable.ic_indoor};
        for (int i = 0; i < storeproduct.length; i++) {
            StoreProductList storeProductList = new StoreProductList();
            storeProductList.setProductImage(storeproduct[i]);
            productLists.add(storeProductList);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.product_move_left) {
            int firstVisibleItemIndex = productListManager.findFirstCompletelyVisibleItemPosition();
            if (firstVisibleItemIndex > 0) {
                productListManager.smoothScrollToPosition(mProductListRecycleView, null, firstVisibleItemIndex - 1);
            }
        }
        if (v.getId() == R.id.product_right_move) {
            int totalItemCount = mProductListRecycleView.getAdapter().getItemCount();
            if (totalItemCount <= 0) return;
            int lastVisibleItemIndex = productListManager.findLastVisibleItemPosition();

            if (lastVisibleItemIndex >= totalItemCount) return;
            productListManager.smoothScrollToPosition(mProductListRecycleView, null, lastVisibleItemIndex + 1);
        }

        if (v.getId() == R.id.size_move_left) {
            int firstVisibleItemIndex = sizeLayoutmanager.findFirstCompletelyVisibleItemPosition();
            if (firstVisibleItemIndex > 0) {
                sizeLayoutmanager.smoothScrollToPosition(mSizeRV, null, firstVisibleItemIndex - 1);
            }
        }
        if (v.getId() == R.id.size_move_right) {
            int totalItemCount = mSizeRV.getAdapter().getItemCount();
            if (totalItemCount <= 0) return;
            int lastVisibleItemIndex = sizeLayoutmanager.findLastVisibleItemPosition();

            if (lastVisibleItemIndex >= totalItemCount) return;
            sizeLayoutmanager.smoothScrollToPosition(mSizeRV, null, lastVisibleItemIndex + 1);
        }
        if (v.getId() == R.id.quality_move_left) {
            int firstVisibleItemIndex = qualityLayoutmanager.findFirstCompletelyVisibleItemPosition();
            if (firstVisibleItemIndex > 0) {
                qualityLayoutmanager.smoothScrollToPosition(mQualityRv, null, firstVisibleItemIndex - 1);
            }
        }
        if (v.getId() == R.id.quality_move_right) {
            int totalItemCount = mQualityRv.getAdapter().getItemCount();
            if (totalItemCount <= 0) return;
            int lastVisibleItemIndex = qualityLayoutmanager.findLastVisibleItemPosition();

            if (lastVisibleItemIndex >= totalItemCount) return;
            qualityLayoutmanager.smoothScrollToPosition(mQualityRv, null, lastVisibleItemIndex + 1);
        }

        if (v.getId() == R.id.product_share) {
           /* currentProductImage.buildDrawingCache();
            mShareImageBitmap = currentProductImage.getDrawingCache();
            shareBitmap(mShareImageBitmap);*/
            if (!Permissive.checkPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {

                new Permissive.Request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .whenPermissionsGranted(new PermissionsGrantedListener() {
                            @Override
                            public void onPermissionsGranted(String[] permissions) throws SecurityException {
                                currentProductImage.setDrawingCacheEnabled(true);
                                currentProductImage.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                                currentProductImage.layout(0, 0, currentProductImage.getMeasuredWidth(), currentProductImage.getMeasuredHeight());

                                currentProductImage.buildDrawingCache(true);
                                mShareImageBitmap = Bitmap.createBitmap(currentProductImage.getDrawingCache());
                                currentProductImage.setDrawingCacheEnabled(false);
                                shareBitmap(mShareImageBitmap);
                            }
                        })
                        .whenPermissionsRefused(new PermissionsRefusedListener() {
                            @Override
                            public void onPermissionsRefused(String[] permissions) {
                                // given permissions are refused

                                Toast.makeText(getActivity(), "Please grant permission", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .execute(getActivity());

            } else {
                currentProductImage.setDrawingCacheEnabled(true);
                currentProductImage.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                currentProductImage.layout(0, 0, currentProductImage.getMeasuredWidth(), currentProductImage.getMeasuredHeight());

                currentProductImage.buildDrawingCache(true);
                mShareImageBitmap = Bitmap.createBitmap(currentProductImage.getDrawingCache());
                currentProductImage.setDrawingCacheEnabled(false);
                shareBitmap(mShareImageBitmap);
            }
        }
        if (v.getId() == R.id.product_like) {
            Toast.makeText(getActivity(), "Product Like", Toast.LENGTH_SHORT).show();
        }
    }

    private void shareBitmap(Bitmap mShareImageBitmap) {
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "Pictures/Thewhiteshope1.png";
        System.out.println("fileString"+filePath);
        File imagePath = new File(filePath);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            mShareImageBitmap.compress(Bitmap.CompressFormat.PNG, 10, fos);
            fos.flush();
            fos.close();
            sendimage(imagePath);
        } catch (FileNotFoundException e) {
            Log.e("file not getting", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("io exce", e.getMessage(), e);
        }
    }

    public void sendimage(File path) {
        System.out.println("filepath:"+path);
        Uri fileUriPath = FileProvider.getUriForFile(getActivity(), getString(R.string.file_provider_authority), path);
        System.out.println("fileUriPath:"+fileUriPath);
        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.setType("image/png");
        /*Uri myUri = Uri.parse("file://" + path);*/
        tweetIntent.putExtra(Intent.EXTRA_STREAM, fileUriPath);
        tweetIntent.putExtra(Intent.EXTRA_TEXT, "sharing product name");
        startActivity(Intent.createChooser(tweetIntent, "Share via"));
    }


}
