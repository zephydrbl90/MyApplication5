package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private int foodQuantity = 0;
    private int drinkQuantity = 0;

    private TextView foodQuantityTextView;
    private TextView drinkQuantityTextView;
    private TextView foodDescriptionTextView;
    private TextView drinkDescriptionTextView;
    private TextView deepDishDescriptionTextView;


    private ImageButton minusFoodButton;
    private ImageButton plusFoodButton;
    private ImageButton minusDrinkButton;
    private ImageButton plusDrinkButton;

    private static final String SHARED_PREFS = "CartPrefs";
    private static final String FOOD_QUANTITY_KEY = "foodQuantity";
    private static final String DRINK_QUANTITY_KEY = "drinkQuantity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Inisialisasi View
        foodQuantityTextView = findViewById(R.id.foodQuantity);
        minusFoodButton = findViewById(R.id.buttonMinusFood);
        plusFoodButton = findViewById(R.id.buttonPlusFood);

        drinkQuantityTextView = findViewById(R.id.drinkQuantity);
        minusDrinkButton = findViewById(R.id.buttonMinusDrink);
        plusDrinkButton = findViewById(R.id.buttonPlusDrink);

        foodDescriptionTextView = findViewById(R.id.foodDescription);
        drinkDescriptionTextView = findViewById(R.id.drinkDescription);
        deepDishDescriptionTextView = findViewById(R.id.deepDishDescription);

        // Load data dari SharedPreferences
        loadQuantitiesFromPreferences();
        updateQuantityDisplay();

        // Set listener untuk tombol minus dan plus
        minusFoodButton.setOnClickListener(v -> {
            if (foodQuantity > 0) {
                foodQuantity--;
                updateQuantityDisplay();
                saveQuantitiesToPreferences();
            }
        });

        plusFoodButton.setOnClickListener(v -> {
            foodQuantity++;
            updateQuantityDisplay();
            saveQuantitiesToPreferences();
        });

        minusDrinkButton.setOnClickListener(v -> {
            if (drinkQuantity > 0) {
                drinkQuantity--;
                updateQuantityDisplay();
                saveQuantitiesToPreferences();
            }
        });

        plusDrinkButton.setOnClickListener(v -> {
            drinkQuantity++;
            updateQuantityDisplay();
            saveQuantitiesToPreferences();
        });

        // Set listener untuk deskripsi
        foodDescriptionTextView.setOnClickListener(v -> {
            String fullDescription = "Authentic Italian pizza with a soft, charred crust, San Marzano tomatoes, and fresh mozzarella.";
            DescriptionDialogFragment dialog = DescriptionDialogFragment.newInstance(fullDescription);
            dialog.show(getSupportFragmentManager(), "DescriptionDialog");
        });

        drinkDescriptionTextView.setOnClickListener(v -> {
            String fullDescription = "Thin, foldable crust with rich tomato sauce and gooey cheese. A true NYC classic!.";
            DescriptionDialogFragment dialog = DescriptionDialogFragment.newInstance(fullDescription);
            dialog.show(getSupportFragmentManager(), "DescriptionDialog");
        });
        deepDishDescriptionTextView.setOnClickListener(v -> {
            String fullDescription = "Thick, buttery crust layered with mozzarella, chunky tomato sauce, and classic toppings.";
            DescriptionDialogFragment dialog = DescriptionDialogFragment.newInstance(fullDescription);
            dialog.show(getSupportFragmentManager(), "DescriptionDialog");
        });
    }

    private void updateQuantityDisplay() {
        foodQuantityTextView.setText(String.valueOf(foodQuantity));
        drinkQuantityTextView.setText(String.valueOf(drinkQuantity));
    }

    private void loadQuantitiesFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        foodQuantity = sharedPreferences.getInt(FOOD_QUANTITY_KEY, 1);
        drinkQuantity = sharedPreferences.getInt(DRINK_QUANTITY_KEY, 1);
    }

    private void saveQuantitiesToPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(FOOD_QUANTITY_KEY, foodQuantity);
        editor.putInt(DRINK_QUANTITY_KEY, drinkQuantity);
        editor.apply();
    }
}