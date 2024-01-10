package com.hdhxby.ecommerce.auth.web.rest;

import com.hdhxby.ecommerce.AuthApplication;
import com.hdhxby.ecommerce.auth.domain.AuthAccount;
import com.hdhxby.ecommerce.auth.mapper.AuthAccountMapper;
import com.hdhxby.ecommerce.auth.repository.AuthAccountRepository;
import io.github.thinkframework.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限REST控制器的测试类.
 *
 * @see AuthAccountResource
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthApplication.class})
public class AuthAccountResourceIT {

    private static final Long DEFAULT_ID = 1L;
    private static final Long UPDATED_ID = 1L;

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/auth/auth-accounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private AuthAccountRepository authAccountRepository;

    @Autowired
    private AuthAccountMapper authAccountMapper;

    @Autowired
    private MockMvc restAuthAccountMockMvc;

    private AuthAccount authAccount;


    /**
     * 为此测试创建一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthAccount createEntity() {
        return AuthAccount.builder()
            .id(DEFAULT_ID)
            .username(DEFAULT_USERNAME)
            .password(DEFAULT_PASSWORD)
            .name(DEFAULT_NAME)
            .remark(DEFAULT_REMARK)
            .build();
    }


    /**
     * 为此测试更新一个实体.
     *
     * 这是一个静态方法,因为其他实体的测试可能也需要它,
     * 如果他们测试需要当前实体的实体.
     */
    public static AuthAccount updateEntity(AuthAccount authAccount) {
        return authAccount.toBuilder()
            .id(UPDATED_ID)
            .username(UPDATED_USERNAME)
            .password(UPDATED_PASSWORD)
            .name(UPDATED_NAME)
            .remark(UPDATED_REMARK)
            .build();
    }

    @Before
    public void initTest() {
        authAccount = createEntity();
    }

    @Test
    @Transactional
    public void createAuthAccount() throws Exception {
        Long databaseSizeBeforeCreate = authAccountRepository.count();

        restAuthAccountMockMvc.perform(post(ENTITY_API_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToJsonBytes(authAccountMapper
                    .toDto(authAccount))))
            .andExpect(status().isCreated());

        assertPersistedAuthAccount(
            authAccounts -> {
                assertThat(authAccounts).hasSize(databaseSizeBeforeCreate.intValue() + 1);
                AuthAccount testauthAccount = authAccounts.get(authAccounts.size() - 1);
                assertThat(testauthAccount.getUsername()).isEqualTo(DEFAULT_USERNAME);
                assertThat(testauthAccount.getPassword()).isEqualTo(DEFAULT_PASSWORD);
                assertThat(testauthAccount.getName()).isEqualTo(DEFAULT_NAME);
                assertThat(testauthAccount.getRemark()).isEqualTo(DEFAULT_REMARK);
            });
    }


    @Test
    @Transactional
    public void updateAuthAccount() throws Exception {
        AuthAccount updatedAuthAccount = authAccountRepository.create(authAccount);

        restAuthAccountMockMvc.perform(put(ENTITY_API_URL_ID,updatedAuthAccount.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToJsonBytes(authAccountMapper
                    .toDto(updateEntity(updatedAuthAccount)))))
            .andExpect(status().isOk());

        assertPersistedAuthAccount(
            authAccounts -> {
                AuthAccount testauthAccount = authAccounts.stream().filter(authAccount ->UPDATED_ID.equals(updatedAuthAccount.getId())).findFirst().get();
                assertThat(testauthAccount.getUsername()).isEqualTo(UPDATED_USERNAME);
                assertThat(testauthAccount.getPassword()).isEqualTo(UPDATED_PASSWORD);
                assertThat(testauthAccount.getName()).isEqualTo(UPDATED_NAME);
                assertThat(testauthAccount.getRemark()).isEqualTo(UPDATED_REMARK);
            });
    }


    @Test
    @Transactional
    public void partialUpdateAuthAccount() throws Exception {
        AuthAccount updatedAuthAccount = authAccountRepository.create(authAccount);

        restAuthAccountMockMvc.perform(patch(ENTITY_API_URL_ID,updatedAuthAccount.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.convertObjectToJsonBytes(authAccountMapper
                    .toDto(updateEntity(updatedAuthAccount)))))
            .andExpect(status().isOk());

        assertPersistedAuthAccount(
            authAccounts -> {
                AuthAccount testauthAccount = authAccounts.stream().filter(authAccount ->UPDATED_ID.equals(updatedAuthAccount.getId())).findFirst().get();
                assertThat(testauthAccount.getUsername()).isEqualTo(DEFAULT_USERNAME);
                assertThat(testauthAccount.getPassword()).isEqualTo(DEFAULT_PASSWORD);
                assertThat(testauthAccount.getName()).isEqualTo(DEFAULT_NAME);
                assertThat(testauthAccount.getRemark()).isEqualTo(DEFAULT_REMARK);
            });
    }

    @Test
    @Transactional
    public void getAuthAccount() throws Exception {
        // 初始化数据库
        authAccountRepository.create(authAccount);

        // 获取账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限
        restAuthAccountMockMvc.perform(get(ENTITY_API_URL_ID, authAccount.getId()))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(authAccount.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
        ;

    }


    @Test
    @Transactional
    public void getAllAuthAccounts() throws Exception {
        // 初始化数据库
        authAccountRepository.create(authAccount);

        // 获取所有的账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限列表
        restAuthAccountMockMvc.perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[*].id").value(hasItem(authAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
        ;
    }

    @Test
    @Transactional
    public void deleteAuthAccountById() throws Exception {
        // 初始化数据库
        AuthAccount testAuthAccount = authAccountRepository.create(authAccount);
        assertThat(authAccountRepository.existsById(testAuthAccount.getId())).isTrue();
        Long databaseSizeBeforeDelete = authAccountRepository.count();

        // 获取账号表，电商公司里一个员工就对应着一个账号，每个账号给分配多个角色，同时这个账号也可以给分配多个权限
        restAuthAccountMockMvc.perform(delete(ENTITY_API_URL_ID, authAccount.getId()))
            .andExpect(status().isNoContent());
        assertThat(authAccountRepository.existsById(authAccount.getId())).isFalse();
        assertPersistedAuthAccount(authAccount -> {
            assertThat(authAccount).hasSize(databaseSizeBeforeDelete.intValue() - 1);
        });
    }

    private void assertPersistedAuthAccount(Consumer<List<AuthAccount>> authAccountAssertion) {
        authAccountAssertion.accept(authAccountRepository.findAll());
    }
}
